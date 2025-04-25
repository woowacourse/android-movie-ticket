package woowacourse.movie.ui.view.booking

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
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presenter.BookingPresenter
import woowacourse.movie.ui.mapper.PosterMapper
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.utils.setImage
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity :
    BaseActivity(),
    BookingContract.View {
    private lateinit var headCountView: TextView
    private lateinit var presenter: BookingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!canLoadMovie()) return
        if (savedInstanceState != null) loadSavedInstanceState(savedInstanceState)
        setupScreen(R.layout.activity_booking)
        showSelectedMovie()
        setupTicketQuantityButtonListeners()
        setupSelectButtonListener()
        presenter.loadAvailableDates()
    }

    override fun showSelectedMovie() {
        val movie = presenter.getMovie()
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
        outState.putInt(KEY_HEAD_COUNT, presenter.getHeadCount())
        outState.putString(KEY_DATE, presenter.selectedDate.toString())
        outState.putString(KEY_TIME, presenter.selectedTime.toString())
    }

    override fun showErrorDialog() {
        AlertDialog
            .Builder(this)
            .setMessage(R.string.movie_not_found_message)
            .setPositiveButton(R.string.confirm) { _, _ -> finish() }
            .setCancelable(false)
            .show()
    }

    override fun showBookingConfirmDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.complete)) { _, _ ->
                presenter.onConfirm()
            }.setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }

    override fun navigateToSummary(movieTicket: MovieTicket) {
        val intent = Intent(this, BookingSummaryActivity::class.java)
        intent.putExtra(getString(R.string.ticket_info_key), movieTicket)
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
                    presenter.saveTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun canLoadMovie(): Boolean {
        val movieId = intent.getIntExtra(getString(R.string.movie_info_key), -1)
        presenter = BookingPresenter(this, movieId)

        return presenter.tryLoadMovie(movieId)
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
            showBookingConfirmDialog()
        }
    }

    private fun loadSavedInstanceState(savedInstance: Bundle) {
        val restoredCount = savedInstance.getInt(KEY_HEAD_COUNT, presenter.getHeadCount())
        presenter.saveHeadCount(restoredCount)

        val restoredDate = LocalDate.parse(savedInstance.getString(KEY_DATE))
        presenter.saveDate(restoredDate)

        val restoredTime = LocalTime.parse(savedInstance.getString(KEY_TIME))
        presenter.saveTime(restoredTime)
    }

    companion object {
        private const val KEY_HEAD_COUNT = "HeadCount"
        private const val KEY_DATE = "Date"
        private const val KEY_TIME = "Time"
    }
}
