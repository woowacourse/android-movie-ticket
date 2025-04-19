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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.PeopleCount
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import woowacourse.movie.view.StringFormatter.dotDateFormat
import java.time.LocalDate
import java.time.LocalDateTime

class BookingActivity : AppCompatActivity() {
    private lateinit var peopleCount: PeopleCount

    private lateinit var movieItem: Movie

    private lateinit var movieTitleView: TextView
    private lateinit var movieReleaseDateView: TextView
    private lateinit var movieRunningTimeView: TextView
    private lateinit var peopleCountView: TextView
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner

    private var selectedDatePosition: Int = 0
    private var selectedTimePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bind()
        applyWindowInsets()

        movieItem = intent.getSerializableExtra(EXTRA_MOVIE) as Movie

        if (savedInstanceState != null) {
            restoreState(savedInstanceState)
        } else {
            setInitialState()
        }

        setupViews(movieItem)
        setDateSpinner(movieItem.releaseDate)
        setButtonListeners()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun bind() {
        movieTitleView = findViewById(R.id.tv_title)
        movieReleaseDateView = findViewById(R.id.tv_release_date)
        movieRunningTimeView = findViewById(R.id.tv_running_time)
        peopleCountView = findViewById(R.id.tv_people_count)
        dateSpinner = findViewById(R.id.sp_date)
        timeSpinner = findViewById(R.id.sp_time)
    }

    private fun setInitialState() {
        peopleCount = PeopleCount(MIN_PEOPLE_COUNT)
        selectedDatePosition = DEFAULT_SPINNER_POSITION
        selectedTimePosition = DEFAULT_SPINNER_POSITION
    }

    private fun restoreState(savedInstanceState: Bundle) {
        peopleCount = savedInstanceState.getSerializable(KEY_PEOPLE_COUNT) as PeopleCount
        selectedDatePosition = savedInstanceState.getInt(KEY_SELECTED_DATE_POSITION, 0)
        selectedTimePosition = savedInstanceState.getInt(KEY_SELECTED_TIME_POSITION, 0)
    }

    private fun updatePeopleCountView() {
        peopleCountView.text = peopleCount.count.toString()
    }

    private fun setupViews(movieItem: Movie) {
        val (startDate, endDate) = movieItem.releaseDate
        val posterView: ImageView = findViewById(R.id.img_movie_poster)

        movieTitleView.text = movieItem.title
        posterView.setImageResource(movieItem.poster)
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

    private fun setButtonListeners() {
        val increaseBtn: Button = findViewById(R.id.btn_increase)
        val decreaseBtn: Button = findViewById(R.id.btn_decrease)
        val bookingBtn: Button = findViewById(R.id.btn_booking_complete)

        increaseBtn.setOnClickListener {
            peopleCount.increase()
            updatePeopleCountView()
        }

        decreaseBtn.setOnClickListener {
            peopleCount.decrease()
            updatePeopleCountView()
        }

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
        val title: String = movieTitleView.text.toString()
        val date: String = dateSpinner.selectedItem.toString()
        val time: String = timeSpinner.selectedItem.toString()
        val count: Int = peopleCount.count

        return BookedTicket(title, PeopleCount(count), "$date $time")
    }

    private fun moveToBookingCompleteActivity() {
        val bookedTicket = madeBookedTicket()
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra(EXTRA_BOOKED_TICKET, bookedTicket)
            }
        startActivity(intent)
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
        outState.putSerializable(KEY_PEOPLE_COUNT, peopleCount)
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    companion object {
        private const val MIN_PEOPLE_COUNT = 1
        private const val DEFAULT_SPINNER_POSITION = 0

        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"

        private const val EXTRA_MOVIE = "movie"
        private const val EXTRA_BOOKED_TICKET = "bookedTicket"
    }
}
