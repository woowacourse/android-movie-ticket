package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BookingCompleteActivity.Companion.KEY_BOOKING_RESULT
import woowacourse.movie.mapper.IntentCompat
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.BookingContract
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.Movie
import woowacourse.movie.presenter.BookingPresenter
import woowacourse.movie.ui.adapter.ScreeningDateSpinnerAdapter
import woowacourse.movie.ui.adapter.ScreeningTimeSpinnerAdapter
import woowacourse.movie.ui.listener.ScreeningDateSelectedListener
import woowacourse.movie.ui.listener.ScreeningTimeSelectedListener
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private lateinit var presenter: BookingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        setUpUi()

        val movieData = requireMovieOrFinish()
        presenter = BookingPresenter(this, movieData)
        presenter.initializeData(savedInstanceState)
        initReserveConfirm()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun requireMovieOrFinish(): Movie {
        return IntentCompat.getParcelableExtra(intent, KEY_MOVIE_DATA, Movie::class.java)
            ?: run {
                showToastErrorAndFinish(getString(R.string.booking_toast_message))
                throw IllegalStateException("영화 정보가 없습니다")
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun showMovieInfo(movie: Movie) {
        val movieUiData = movie.toUiModel(resources)
        val moviePoster = findViewById<ImageView>(R.id.img_booking_poster)
        val bookingTitle = findViewById<TextView>(R.id.tv_booking_title)
        val bookingScreenDate = findViewById<TextView>(R.id.tv_booking_screening_date)
        val bookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)

        bookingTitle.text = movieUiData.title
        moviePoster.setImageResource(movieUiData.imageSource)
        bookingScreenDate.text = movieUiData.screeningPeriod
        bookingRunningTime.text = movieUiData.runningTimeText
    }

    override fun showBookingResult(result: BookingResult) {
        val headCountText = findViewById<TextView>(R.id.tv_people_count)
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        val btnMinus = findViewById<Button>(R.id.btn_minus)

        headCountText.text = result.headCount.toString()

        btnPlus.setOnClickListener { presenter.onHeadCountIncreased() }
        btnMinus.setOnClickListener { presenter.onHeadCountDecreased() }
    }

    override fun showScreeningDates(
        dates: List<LocalDate>,
        selected: LocalDate,
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        dateSpinner.adapter = ScreeningDateSpinnerAdapter(this, dates)

        val position = dates.indexOf(selected)
        if (position != -1) {
            dateSpinner.setSelection(position)
        }

        dateSpinner.onItemSelectedListener =
            ScreeningDateSelectedListener(
                onDateSelected = { date ->
                    presenter.onDateSelected(date)
                },
            )
    }

    override fun showScreeningTimes(
        times: List<LocalTime>,
        selected: LocalTime,
    ) {
        val timeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        timeSpinner.adapter = ScreeningTimeSpinnerAdapter(this, times)

        val position = times.indexOf(selected)
        if (position != -1) {
            timeSpinner.setSelection(position)
        }

        timeSpinner.onItemSelectedListener =
            ScreeningTimeSelectedListener(
                onTimeSelected = { time ->
                    presenter.onTimeSelected(time)
                },
            )
    }

    private fun initReserveConfirm() {
        val btnReserveConfirm = findViewById<Button>(R.id.btn_reserve_confirm)
        btnReserveConfirm.setOnClickListener {
            presenter.onConfirmReservation()
        }
    }

    override fun showBookingResultDialog(result: BookingResult) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                val intent = Intent(this, BookingCompleteActivity::class.java)
                intent.putExtra(KEY_BOOKING_RESULT, result)
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.dig_btn_negative_message)) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }

    override fun showToastErrorAndFinish(message: String) {
        Log.d("BookingActivity", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        presenter.onSaveState(outState)
    }

    companion object {
        const val KEY_MOVIE_DATA = "movieData"
    }
}
