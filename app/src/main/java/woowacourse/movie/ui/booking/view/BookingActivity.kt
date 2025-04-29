package woowacourse.movie.ui.booking.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.booking.contract.BookingContract
import woowacourse.movie.ui.booking.presenter.BookingPresenter
import woowacourse.movie.ui.seat.BookingSeatActivity
import woowacourse.movie.utils.StringFormatter.dotDateFormat
import woowacourse.movie.utils.bundleSerializable
import woowacourse.movie.utils.intentSerializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingActivity :
    AppCompatActivity(),
    BookingContract.View {
    private val bookingPresenter = BookingPresenter(this)

    private val peopleCountView: TextView by lazy { findViewById(R.id.tv_headcount) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        applyWindowInsets()

        bookingPresenter.loadMovie()
        bookingPresenter.updateViews()
        setButtonClickListeners()
    }

    override fun getMovie(): Movie? = intent.intentSerializable(EXTRA_MOVIE, Movie::class.java)

    override fun getSelectedDateTime(): LocalDateTime =
        LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate,
            timeSpinner.selectedItem as LocalTime,
        )

    override fun getSelectedDate(): LocalDate = dateSpinner.selectedItem as LocalDate

    override fun getSelectedTimePosition(): Int = timeSpinner.selectedItemPosition

    override fun setMovieInfoViews(movie: Movie) {
        val movieTitleView: TextView = findViewById(R.id.tv_title)
        val (startDate, endDate) = movie.releaseDate
        val posterView: ImageView = findViewById(R.id.img_movie_poster)
        val movieReleaseDateView: TextView = findViewById(R.id.tv_screening_period)
        val movieRunningTimeView: TextView = findViewById(R.id.tv_running_time)

        movieTitleView.text = movie.title
        posterView.setImageResource(movie.posterId)
        movieReleaseDateView.text =
            getString(R.string.text_date_period)
                .format(dotDateFormat(startDate), dotDateFormat(endDate))
        movieRunningTimeView.text = getString(R.string.text_minute).format(movie.runningTime)
        bookingPresenter.refreshHeadcountDisplay()
    }

    override fun updateHeadcountDisplay(headcount: Headcount) {
        peopleCountView.text = headcount.count.toString()
    }

    override fun setDateSpinner(
        spinnerItems: List<LocalDate>,
        position: Int,
    ) {
        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    spinnerItems,
                )
            setSelection(position)

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    bookingPresenter.setupTimeSpinner()
                }
        }
        bookingPresenter.setupTimeSpinner()
    }

    override fun setTimeSpinner(
        spinnerItems: List<LocalTime>,
        position: Int,
    ) {
        with(timeSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    spinnerItems,
                )
            if (spinnerItems.isNotEmpty()) {
                setSelection(position)
            }
            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                }
        }
    }

    override fun startBookingSeatActivity(
        movieTitle: String,
        dateTime: LocalDateTime,
        headcount: Headcount,
    ) {
        startActivity(BookingSeatActivity.newIntent(this, movieTitle, dateTime, headcount))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_PEOPLE_COUNT, bookingPresenter.headcount)
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val headcount =
            savedInstanceState.bundleSerializable(
                KEY_PEOPLE_COUNT,
                Headcount::class.java,
            ) as Headcount
        val selectedDatePosition: Int = savedInstanceState.getInt(KEY_SELECTED_DATE_POSITION)
        val selectedTimePosition: Int = savedInstanceState.getInt(KEY_SELECTED_TIME_POSITION)

        bookingPresenter.restoreHeadcount(headcount)
        bookingPresenter.setSelectedDatePosition(selectedDatePosition)
        bookingPresenter.setSelectedTimePosition(selectedTimePosition)
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setButtonClickListeners() {
        setIncreaseButtonClickListener()
        setDecreaseButtonClickListener()
        setBookingCompleteButtonClickListener()
    }

    private fun setIncreaseButtonClickListener() {
        val increaseBtn: Button = findViewById(R.id.btn_increase)
        increaseBtn.setOnClickListener {
            bookingPresenter.increaseHeadcount()
            bookingPresenter.refreshHeadcountDisplay()
        }
    }

    private fun setDecreaseButtonClickListener() {
        val decreaseBtn: Button = findViewById(R.id.btn_decrease)
        decreaseBtn.setOnClickListener {
            bookingPresenter.decreaseHeadcount()
            bookingPresenter.refreshHeadcountDisplay()
        }
    }

    private fun setBookingCompleteButtonClickListener() {
        val bookingCompleteBtn: Button = findViewById(R.id.btn_booking_complete)
        bookingCompleteBtn.setOnClickListener {
            bookingPresenter.completeBooking()
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, BookingActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }

        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"

        private const val EXTRA_MOVIE = "movie"
    }
}
