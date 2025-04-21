package woowacourse.movie.view.booking

import android.app.AlertDialog
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
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import woowacourse.movie.utils.StringFormatter.dotDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

class BookingActivity : AppCompatActivity() {
    private lateinit var headcount: Headcount
    private lateinit var movieItem: Movie

    private val peopleCountView: TextView by lazy { findViewById(R.id.tv_headcount) }
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }

    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        applyWindowInsets()

        movieItem = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        } else {
            setInitialState()
        }

        setupViews(movieItem)
        setDateSpinner(movieItem.releaseDate)
        setButtonClickListeners()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setInitialState() {
        headcount = Headcount()
        selectedDatePosition = DEFAULT_SPINNER_POSITION
        selectedTimePosition = DEFAULT_SPINNER_POSITION
    }

    private fun restoreState(savedInstanceState: Bundle) {
        headcount = savedInstanceState.getSerializable(KEY_PEOPLE_COUNT) as Headcount
        selectedDatePosition = savedInstanceState.getInt(KEY_SELECTED_DATE_POSITION, 0)
        selectedTimePosition = savedInstanceState.getInt(KEY_SELECTED_TIME_POSITION, 0)
    }

    private fun updatePeopleCountView() {
        peopleCountView.text = headcount.count.toString()
    }

    private fun setupViews(movieItem: Movie) {
        val (startDate, endDate) = movieItem.releaseDate
        val posterView: ImageView = findViewById(R.id.img_movie_poster)
        val movieTitleView: TextView = findViewById(R.id.tv_title)
        val movieReleaseDateView: TextView = findViewById(R.id.tv_screening_period)
        val movieRunningTimeView: TextView = findViewById(R.id.tv_running_time)

        movieTitleView.text = movieItem.title
        posterView.setImageResource(movieItem.posterId)
        movieReleaseDateView.text =
            getString(R.string.text_date_period)
                .format(dotDateFormat(startDate), dotDateFormat(endDate))
        movieRunningTimeView.text = movieItem.runningTime
        updatePeopleCountView()
    }

    private fun setDateSpinner(releaseDate: ScreeningDate) {
        val (startDate, endDate) = releaseDate
        val screeningBookingDates = ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingDates,
                )
            setSelection(selectedDatePosition)

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    selectedDatePosition = pos
                    setTimeSpinner(screeningBookingDates[pos])
                }
        }
        setTimeSpinner(screeningBookingDates[selectedDatePosition])
    }

    private fun setTimeSpinner(selectedDate: LocalDate) {
        val screeningTimes =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        with(timeSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningTimes,
                )
            setSelection(selectedTimePosition)

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    selectedTimePosition = pos
                }
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
            headcount.increase()
            updatePeopleCountView()
        }
    }

    private fun setDecreaseButtonClickListener() {
        val decreaseBtn: Button = findViewById(R.id.btn_decrease)
        decreaseBtn.setOnClickListener {
            headcount.decrease()
            updatePeopleCountView()
        }
    }

    private fun setBookingCompleteButtonClickListener() {
        val bookingCompleteBtn: Button = findViewById(R.id.btn_booking_complete)
        bookingCompleteBtn.setOnClickListener {
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
        AlertDialog
            .Builder(this)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(getString(R.string.text_booking_dialog_positive_button)) { _, _ ->
                moveToBookingCompleteActivity()
            }.setNegativeButton(getString(R.string.text_booking_dialog_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }

    private fun madeBookedTicket(): BookedTicket {
        val title: String = movieItem.title
        val date: String = dateSpinner.selectedItem.toString()
        val time: String = timeSpinner.selectedItem.toString()
        val count: Int = headcount.count

        return BookedTicket(title, Headcount(count), "$date $time")
    }

    private fun moveToBookingCompleteActivity() {
        val bookedTicket = madeBookedTicket()
        startActivity(BookingCompleteActivity.newIntent(this, bookedTicket))
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
        outState.putSerializable(KEY_PEOPLE_COUNT, headcount)
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    companion object {
        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, BookingActivity::class.java).apply {
                putExtra(EXTRA_MOVIE, movie)
            }

        private const val DEFAULT_SPINNER_POSITION = 0

        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"

        private const val EXTRA_MOVIE = "movie"
    }
}
