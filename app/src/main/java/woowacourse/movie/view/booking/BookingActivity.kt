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
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.ScreeningDate
import woowacourse.movie.domain.model.booking.ScreeningTime
import woowacourse.movie.domain.model.booking.TicketType
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.domain.model.movies.Poster
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.booking.BookingContract.PresenterFactory
import woowacourse.movie.view.movies.MovieListActivity.Companion.KEY_MOVIE
import java.time.LocalDate
import java.time.LocalDateTime

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private val presenter by lazy { PresenterFactory.providePresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        val movieIdx = intent.getIntExtra(KEY_MOVIE, NO_MOVIE)

        initView(movieIdx)
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

    override fun showMovieDetail(movie: Movie) {
        with(movie) {
            val (startDate, endDate) = releaseDate
            initTitleView(title)
            initPosterView(posterResource)
            initReleaseDateView(startDate, endDate)
            initRunningTimeView(runningTime)
            initDateSpinner(startDate, endDate)
        }
    }

    override fun showPeopleCount(count: Int) {
        findViewById<TextView>(R.id.tv_people_count).text = count.toString()
    }

    override fun onClickIncrease() {
        presenter.increasePeopleCount()
    }

    override fun onClickDecrease() {
        presenter.decreasePeopleCount()
    }

    override fun onClickBooking() {
        presenter.loadBooking(
            title = findViewById<TextView>(R.id.tv_title).text.toString(),
            bookingDate = findViewById<Spinner>(R.id.sp_date).selectedItem.toString(),
            bookingTime = findViewById<Spinner>(R.id.sp_time).selectedItem.toString(),
            peopleCount = findViewById<TextView>(R.id.tv_people_count).text.toString(),
            ticketType = TicketType.GENERAL,
        )
    }

    override fun moveToBookingComplete(booking: Booking) {
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra(KEY_BOOKING, booking)
            }
        startActivity(intent)
    }

    private fun initTitleView(title: String) {
        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = title
    }

    private fun initPosterView(poster: Poster) {
        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        poster.posterId?.let { moviePosterView.setImageResource(it) }
    }

    private fun initReleaseDateView(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        val movieReleaseDateView = findViewById<TextView>(R.id.tv_screening_period)
        movieReleaseDateView.text =
            getString(R.string.text_date_period).format(
                StringFormatter.dotDateFormat(startDate),
                StringFormatter.dotDateFormat(endDate),
            )
    }

    private fun initRunningTimeView(runningTime: Int) {
        val movieRunningTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieRunningTimeView.text =
            getString(R.string.text_running_time_ㅡminute_unit).format(runningTime)
    }

    private fun restoreSavedState(
        savedInstanceState: Bundle?,
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        var savedDatePosition: Int? = null
        var savedTimePosition: Int? = null
        savedInstanceState?.let {
            val count = it.getString(KEY_PEOPLE_COUNT)
            savedDatePosition = it.getInt(KEY_SELECTED_DATE_POSITION)
            savedTimePosition = it.getInt(KEY_SELECTED_TIME_POSITION)
            findViewById<TextView>(R.id.tv_people_count).text = count
        }

        // initDateSpinner(startDate, endDate, savedDatePosition, savedTimePosition)
    }

    private fun initDateSpinner(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.sp_date)

        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingDates,
                )

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    initTimeSpinner(screeningBookingDates[pos])
                }
        }
    }

    private fun initTimeSpinner(selectedDate: LocalDate) {
        val timeSpinner: Spinner = findViewById(R.id.sp_time)
        val now = LocalDateTime.now()
        val screeningTime = ScreeningTime(now, selectedDate)

        val availableTimes = screeningTime.getAvailableScreeningTimes()
        if (availableTimes.isEmpty()) {
            showToast(R.string.text_no_booking_time)
            return
        }

        with(timeSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    availableTimes,
                )
            // setSelection(savedPosition ?: 0)
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

    private fun showToast(
        @StringRes res: Int,
    ) {
        Toast.makeText(this, res, Toast.LENGTH_LONG).show()
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

        val dateSpinner = findViewById<Spinner>(R.id.sp_date)
        val timeSpinner = findViewById<Spinner>(R.id.sp_time)
        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        outState.putString(KEY_PEOPLE_COUNT, peopleCount.text.toString())
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    companion object {
        const val KEY_BOOKING = "BOOKING"

        private const val NO_MOVIE = -1
        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"
    }
}
