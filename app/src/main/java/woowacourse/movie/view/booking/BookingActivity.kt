package woowacourse.movie.view.booking

import AdapterItemSelectedListener
import android.content.Context
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
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.ext.getSerializable
import woowacourse.movie.view.ext.toDrawableResourceId
import woowacourse.movie.view.seat.SeatActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private lateinit var presenter: BookingContract.Presenter
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.tv_title) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val peopleCountTextView: TextView by lazy { findViewById(R.id.tv_people_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        val movie = intent.getSerializable(KEY_MOVIE, Movie::class.java)
        presenter = BookingPresenter(this, movie, PeopleCount())

        initView()
        savedInstanceState?.let {
            presenter.restorePeopleCount(it.getInt(KEY_PEOPLE_COUNT))
            val savedTimePosition = it.getInt(KEY_SELECTED_TIME_POSITION)
            timeSpinner.setSelection(savedTimePosition)
        }
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadMovieDetail()

        initButtonListener()
    }

    override fun showMovieDetail(movie: Movie) {
        with(movie) {
            val (startDate, endDate) = releaseDate
            initTitleView(title)
            initPosterView(posterResource)
            initReleaseDateView(startDate, endDate)
            initRunningTimeView(runningTime)
        }
    }

    override fun showPeopleCount(count: Int) {
        peopleCountTextView.text = count.toString()
    }

    override fun showScreeningDate(screeningBookingDates: List<LocalDate>) {
        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingDates,
                )

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    presenter.loadScreeningTime(screeningBookingDates[pos], LocalDateTime.now())
                }
        }
    }

    override fun showScreeningTime(screeningBookingTimes: List<LocalTime>) {
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

    override fun moveToBookingComplete(booking: Booking) {
        val intent = SeatActivity.newIntent(this, booking)
        startActivity(intent)
    }

    private fun initTitleView(title: String) {
        movieTitleTextView.text = title
    }

    private fun initPosterView(imgName: String) {
        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        moviePosterView.setImageResource(imgName.toDrawableResourceId(this@BookingActivity))
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

    private fun initButtonListener() {
        val increaseBtn = findViewById<Button>(R.id.btn_increase)
        val decreaseBtn = findViewById<Button>(R.id.btn_decrease)
        val bookingBtn = findViewById<Button>(R.id.btn_booking_complete)

        increaseBtn.setOnClickListener { presenter.increasePeopleCount(MAX_SEAT) }
        decreaseBtn.setOnClickListener { presenter.decreasePeopleCount() }

        bookingBtn.setOnClickListener {
            presenter.loadBooking(
                title = movieTitleTextView.text.toString(),
                bookingDate = dateSpinner.selectedItem.toString(),
                bookingTime = timeSpinner.selectedItem.toString(),
                peopleCount = peopleCountTextView.text.toString(),
            )
        }
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
        const val KEY_MOVIE = "MOVIE"

        private const val NO_MOVIE = -1
        private const val MAX_SEAT = 20

        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, BookingActivity::class.java).apply {
                putExtra(KEY_MOVIE, movie)
            }
    }
}
