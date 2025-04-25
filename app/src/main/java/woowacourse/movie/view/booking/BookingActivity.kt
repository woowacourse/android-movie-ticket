package woowacourse.movie.view.booking

import AdapterItemSelectedListener
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.view.booking.BookingContract.PresenterFactory
import woowacourse.movie.view.ext.toDrawableResourceId
import woowacourse.movie.view.movies.MovieListActivity.Companion.KEY_MOVIE
import woowacourse.movie.view.movies.model.UiModel
import java.time.LocalDateTime

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.tv_title) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val peopleCountTextView: TextView by lazy { findViewById(R.id.tv_people_count) }

    private val presenter by lazy { PresenterFactory.providePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        val movieIdx = intent.getIntExtra(KEY_MOVIE, NO_MOVIE)

        initView(movieIdx)
        savedInstanceState?.let {
            restoreSavedState(it)
        }
    }

    private fun initView(movieIdx: Int) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadMovieDetail(movieIdx)
        presenter.loadPeopleCount()

        initButtonListener()
    }

    override fun showMovieDetail(movie: UiModel.MovieUiModel) {
        with(movie) {
            initTitleView(title)
            initPosterView(imgName)
            initReleaseDateView(releaseStartDate, releaseEndDate)
            initRunningTimeView(runningTime)
            presenter.loadScreeningDate(releaseStartDate, releaseEndDate, LocalDateTime.now())
            presenter.loadScreeningTime(dateSpinner.selectedItem.toString(), LocalDateTime.now())
        }
    }

    override fun showPeopleCount(count: Int) {
        peopleCountTextView.text = count.toString()
    }

    override fun showScreeningDate(screeningBookingDates: List<String>) {
        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingDates,
                )

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    onSelectDate(screeningBookingDates[pos])
                }
        }
    }

    override fun showScreeningTime(screeningBookingTimes: List<String>) {
        with(timeSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingTimes,
                )
        }
    }

    override fun showToast() {
        Toast.makeText(this, R.string.text_no_booking_time, Toast.LENGTH_LONG).show()
    }

    override fun onClickIncrease() {
        presenter.increasePeopleCount()
    }

    override fun onClickDecrease() {
        presenter.decreasePeopleCount()
    }

    override fun onClickBooking() {
        presenter.loadBooking(
            title = movieTitleTextView.text.toString(),
            bookingDate = dateSpinner.selectedItem.toString(),
            bookingTime = timeSpinner.selectedItem.toString(),
            peopleCount = peopleCountTextView.text.toString(),
        )
    }

    override fun onSelectDate(selectedDate: String) {
        presenter.loadScreeningTime(selectedDate, LocalDateTime.now())
    }

    override fun moveToBookingComplete(booking: Booking) {
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra(KEY_BOOKING, booking)
            }
        startActivity(intent)
    }

    override fun restoreSavedState(savedCount: Int) {
        presenter.restorePeopleCount(savedCount)
    }

    private fun initTitleView(title: String) {
        movieTitleTextView.text = title
    }

    private fun initPosterView(imgName: String) {
        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        moviePosterView.setImageResource(imgName.toDrawableResourceId(this@BookingActivity))
    }

    private fun initReleaseDateView(
        startDate: String,
        endDate: String,
    ) {
        val movieReleaseDateView = findViewById<TextView>(R.id.tv_screening_period)
        movieReleaseDateView.text = getString(R.string.text_date_period).format(startDate, endDate)
    }

    private fun initRunningTimeView(runningTime: Int) {
        val movieRunningTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieRunningTimeView.text =
            getString(R.string.text_running_time_ㅡminute_unit).format(runningTime)
    }

    private fun restoreSavedState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            presenter.restorePeopleCount(it.getInt(KEY_PEOPLE_COUNT))
            val savedTimePosition = it.getInt(KEY_SELECTED_TIME_POSITION)
            timeSpinner.setSelection(savedTimePosition)
        }
    }

    private fun initButtonListener() {
        val increaseBtn = findViewById<Button>(R.id.btn_increase)
        val decreaseBtn = findViewById<Button>(R.id.btn_decrease)
        val bookingBtn = findViewById<Button>(R.id.btn_booking_complete)

        increaseBtn.setOnClickListener { onClickIncrease() }
        decreaseBtn.setOnClickListener { onClickDecrease() }

        bookingBtn.setOnClickListener {
            showDialog(
                getString(R.string.text_booking_dialog_title),
                getString(R.string.text_booking_dialog_description),
            )
        }
    }

    private fun showDialog(
        title: String,
        description: String,
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(R.string.text_booking_dialog_positive_button) { _, _ ->
                onClickBooking()
            }
            .setNegativeButton(R.string.text_booking_dialog_negative_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_PEOPLE_COUNT, peopleCountTextView.text.toString().toInt())
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    companion object {
        const val KEY_BOOKING = "BOOKING"

        private const val NO_MOVIE = -1

        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"
    }
}
