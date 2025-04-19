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
import java.time.LocalTime
import kotlin.math.max

class BookingActivity : AppCompatActivity() {
    private lateinit var movieTitleView: TextView
    private lateinit var movieReleaseDateView: TextView
    private lateinit var movieRunningTimeView: TextView
    private lateinit var peopleCountView: TextView
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieItem: Movie = intent.getSerializableExtra("movie") as Movie
        val peopleCount = PeopleCount(intent.getStringExtra(KEY_PEOPLE_COUNT)?.toInt() ?: MIN_BOOKING_PEOPLE_COUNT)
        val selectedDatePosition = intent.getIntExtra(KEY_SELECTED_DATE_POSITION, 0)
        val selectedTimePosition = intent.getIntExtra(KEY_SELECTED_TIME_POSITION, 0)

        bind()
        initViews(movieItem, peopleCount)
        setDateSpinner(movieItem.releaseDate, selectedDatePosition, selectedTimePosition)
        setButtonListeners()
    }

    private fun bind() {
        movieTitleView = findViewById(R.id.tv_title)
        movieReleaseDateView = findViewById(R.id.tv_release_date)
        movieRunningTimeView = findViewById(R.id.tv_running_time)
        peopleCountView = findViewById(R.id.tv_people_count)
        dateSpinner = findViewById(R.id.sp_date)
        timeSpinner = findViewById(R.id.sp_time)
    }

    private fun initViews(
        movieItem: Movie,
        peopleCount: PeopleCount,
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val (startDate, endDate) = movieItem.releaseDate
        val posterView: ImageView = findViewById(R.id.img_movie_poster)

        movieTitleView.text = movieItem.title
        posterView.setImageResource(movieItem.poster)
        movieReleaseDateView.text =
            getString(R.string.text_date_period).format(
                dotDateFormat(startDate),
                dotDateFormat(endDate),
            )
        movieRunningTimeView.text = movieItem.runningTime
        peopleCountView.text = peopleCount.count.toString()
    }

    private fun setDateSpinner(
        releaseDate: ScreeningDate,
        datePosition: Int,
        timePosition: Int,
    ) {
        val (startDate, endDate) = releaseDate
        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        with(dateSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningBookingDates,
                )

            setSelection(datePosition)

            onItemSelectedListener =
                AdapterItemSelectedListener { pos ->
                    setTimeSpinner(screeningBookingDates[pos], timePosition)
                }
        }
    }

    private fun setTimeSpinner(
        selectedDate: LocalDate,
        position: Int,
    ) {
        val screeningTimes: List<LocalTime> =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        with(timeSpinner) {
            adapter =
                ArrayAdapter(
                    this@BookingActivity,
                    android.R.layout.simple_spinner_item,
                    screeningTimes,
                )

            setSelection(position)
        }
    }

    private fun setButtonListeners() {
        val increaseBtn: Button = findViewById(R.id.btn_increase)
        val decreaseBtn: Button = findViewById(R.id.btn_decrease)
        val bookingBtn: Button = findViewById(R.id.btn_booking)

        increaseBtn.setOnClickListener {
            val count = peopleCountView.text.toString().toInt() + MIN_BOOKING_PEOPLE_COUNT
            peopleCountView.text = count.toString()
        }

        decreaseBtn.setOnClickListener {
            val count = max(MIN_BOOKING_PEOPLE_COUNT, peopleCountView.text.toString().toInt() - MIN_BOOKING_PEOPLE_COUNT)
            peopleCountView.text = count.toString()
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

    private fun moveToBookingCompleteActivity() {
        val title: String = movieTitleView.text.toString()
        val date: String = dateSpinner.selectedItem.toString()
        val time: String = timeSpinner.selectedItem.toString()
        val count: Int = peopleCountView.text.toString().toInt()

        val bookedTicket = BookedTicket(title, PeopleCount(count), "$date $time")
        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra("bookedTicket", bookedTicket)
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

        outState.putString(KEY_PEOPLE_COUNT, peopleCountView.text.toString())
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val movieItem: Movie = intent.getSerializableExtra("movie") as Movie
        val peopleCount = PeopleCount(savedInstanceState.getString(KEY_PEOPLE_COUNT)?.toInt() ?: MIN_BOOKING_PEOPLE_COUNT)

        initViews(movieItem, peopleCount)
    }

    companion object {
        private const val MIN_BOOKING_PEOPLE_COUNT = 1

        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"
    }
}
