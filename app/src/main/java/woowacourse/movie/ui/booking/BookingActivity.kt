package woowacourse.movie.ui.booking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R
import woowacourse.movie.domain.movies.Movie
import woowacourse.movie.domain.movies.MovieToReserve
import woowacourse.movie.ui.BaseActivity
import woowacourse.movie.ui.movies.poster.PosterMapper
import woowacourse.movie.ui.movies.poster.setImage
import woowacourse.movie.ui.seat.SeatsSelectionActivity
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity :
    BaseActivity(),
    BookingContract.View {
    private lateinit var headCountView: TextView
    private lateinit var presenter: BookingPresenter
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_booking)
        val movieId = intent.getIntExtra(getString(R.string.movie_info_key), -1)
        presenter = BookingPresenter(this, movieId)
        dateSpinner = findViewById(R.id.dateSpinner)
        timeSpinner = findViewById(R.id.timeSpinner)
        if (!canLoadMovie(movieId)) return
        if (savedInstanceState != null) restoredBookingInfo(savedInstanceState)
        presenter.loadSelectedMovie()
        setupTicketQuantityButtonListeners()
        setupSelectButtonListener()
        presenter.loadAvailableDates()
    }

    override fun showSelectedMovie(movie: Movie) {
        val imagePoster = findViewById<ImageView>(R.id.poster)
        val posterRes = PosterMapper.mapMovieIdToDrawableRes(movie.id)
        imagePoster.setImage(posterRes)

        val title = findViewById<TextView>(R.id.title)
        title.text = movie.title

        val screeningDate = findViewById<TextView>(R.id.screeningDate)
        screeningDate.text =
            getString(R.string.date_text, movie.startScreeningDate, movie.endScreeningDate)

        val runningTime = findViewById<TextView>(R.id.runningTime)
        runningTime.text = getString(R.string.runningTime_text, movie.runningTime.toString())
    }

    override fun updateHeadCount(headCount: Int) {
        headCountView.text = headCount.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val headCount: Int = headCountView.text.toString().toInt()
        val selectedDateItem = dateSpinner.selectedItem as LocalDate
        val selectedTimeItem = timeSpinner.selectedItem as LocalTime
        outState.putInt(KEY_HEAD_COUNT, headCount)
        outState.putString(KEY_DATE, selectedDateItem.toString())
        outState.putString(KEY_TIME, selectedTimeItem.toString())
    }

    override fun showErrorDialog() {
        AlertDialog
            .Builder(this)
            .setMessage(R.string.movie_not_found_message)
            .setPositiveButton(R.string.confirm) { _, _ -> finish() }
            .setCancelable(false)
            .show()
    }

    override fun navigateToSeatsSelection(movieToReserve: MovieToReserve) {
        val intent = Intent(this, SeatsSelectionActivity::class.java)
        intent.putExtra(getString(R.string.reserved_movie_info_key), movieToReserve)
        startActivity(intent)
    }

    override fun updateDateSpinner(
        dates: List<LocalDate>,
        index: Int,
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.dateSpinner)
        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                dates,
            )
        dateSpinner.setSelection(index)

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    presenter.loadAvailableTimes(dates[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    override fun updateTimeSpinner(
        times: List<LocalTime>,
        index: Int,
    ) {
        val timeSpinner = findViewById<Spinner>(R.id.timeSpinner)

        timeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                times,
            )

        timeSpinner.setSelection(index)

        timeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedTime = timeSpinner.getItemAtPosition(position) as LocalTime
                    presenter.restoreTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun canLoadMovie(movieId: Int): Boolean =
        if (movieId == -1) {
            showErrorDialog()
            false
        } else {
            true
        }

    private fun setupTicketQuantityButtonListeners() {
        headCountView = findViewById(R.id.headCount)
        presenter.loadInitialHeadCount()
        val increaseBtn = findViewById<Button>(R.id.increase)
        increaseBtn.setOnClickListener {
            presenter.increaseHeadCount()
        }
        val decreaseBtn = findViewById<Button>(R.id.decrease)
        decreaseBtn.setOnClickListener {
            presenter.decreaseHeadCount()
        }
    }

    private fun setupSelectButtonListener() {
        val selectBtn = findViewById<Button>(R.id.select)
        selectBtn.setOnClickListener {
            presenter.confirmMovieReservation()
        }
    }

    private fun restoredBookingInfo(savedInstance: Bundle) {
        val restoredHeadCount = savedInstance.getInt(KEY_HEAD_COUNT)
        val restoredDate = savedInstance.getString(KEY_DATE)?.let { LocalDate.parse(it) }
        val restoredTime = savedInstance.getString(KEY_TIME)?.let { LocalTime.parse(it) }
        presenter.restoreHeadCount(restoredHeadCount)
        presenter.restoreDate(restoredDate)
        presenter.restoreTime(restoredTime)
    }

    companion object {
        private const val KEY_HEAD_COUNT = "HeadCount"
        private const val KEY_DATE = "Date"
        private const val KEY_TIME = "Time"
    }
}
