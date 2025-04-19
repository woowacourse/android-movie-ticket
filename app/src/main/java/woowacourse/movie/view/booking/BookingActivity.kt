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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        val movieItem: Movie = intent.getSerializableExtra("movie") as Movie
        val peopleCount = PeopleCount(intent.getStringExtra(KEY_PEOPLE_COUNT)?.toInt() ?: 1)
        val selectedDatePosition = intent.getIntExtra(KEY_SELECTED_DATE_POSITION, 0)
        val selectedTimePosition = intent.getIntExtra(KEY_SELECTED_TIME_POSITION, 0)

        initView(movieItem, peopleCount)
        setDateSpinner(movieItem.releaseDate, selectedDatePosition, selectedTimePosition)
        setButtonListener()
    }

    private fun initView(
        movieItem: Movie,
        peopleCount: PeopleCount,
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = movieItem.title

        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        moviePosterView.setImageResource(movieItem.poster)

        val (startDate, endDate) = movieItem.releaseDate

        val movieReleaseDateView = findViewById<TextView>(R.id.tv_screening_period)
        movieReleaseDateView.text =
            getString(R.string.text_date_period).format(
                dotDateFormat(startDate),
                dotDateFormat(endDate),
            )

        val movieRunningTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieRunningTimeView.text = movieItem.runningTime

        findViewById<TextView>(R.id.tv_people_count).text = peopleCount.count.toString()
    }

    private fun setDateSpinner(
        releaseDate: ScreeningDate,
        datePosition: Int,
        timePosition: Int,
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.sp_date)

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
        val timeSpinner: Spinner = findViewById(R.id.sp_time)

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

    private fun setButtonListener() {
        val increaseBtn = findViewById<Button>(R.id.btn_increase)
        val decreaseBtn = findViewById<Button>(R.id.btn_decrease)
        val bookingBtn = findViewById<Button>(R.id.btn_booking_complete)

        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        increaseBtn.setOnClickListener {
            val count = peopleCount.text.toString().toInt() + 1
            peopleCount.text = count.toString()
        }

        decreaseBtn.setOnClickListener {
            val count = max(MIN_BOOKING_PEOPLE_COUNT, peopleCount.text.toString().toInt() - 1)
            peopleCount.text = count.toString()
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
        val title = findViewById<TextView>(R.id.tv_title).text
        val date = findViewById<Spinner>(R.id.sp_date).selectedItem
        val time = findViewById<Spinner>(R.id.sp_time).selectedItem
        val count = findViewById<TextView>(R.id.tv_people_count).text.toString().toInt()

        val bookedTicket = BookedTicket(title.toString(), PeopleCount(count), "$date $time")
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

        val dateSpinner = findViewById<Spinner>(R.id.sp_date)
        val timeSpinner = findViewById<Spinner>(R.id.sp_time)
        val peopleCount = findViewById<TextView>(R.id.tv_people_count)

        outState.putString(KEY_PEOPLE_COUNT, peopleCount.text.toString())
        outState.putInt(KEY_SELECTED_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val movieItem: Movie = intent.getSerializableExtra("movie") as Movie
        val peopleCount = PeopleCount(savedInstanceState.getString(KEY_PEOPLE_COUNT)?.toInt() ?: 1)

        initView(movieItem, peopleCount)
    }

    companion object {
        private const val MIN_BOOKING_PEOPLE_COUNT = 1

        private const val KEY_SELECTED_DATE_POSITION = "SELECTED_DATE_POSITION"
        private const val KEY_SELECTED_TIME_POSITION = "SELECTED_TIME_POSITION"
        private const val KEY_PEOPLE_COUNT = "SAVED_PEOPLE_COUNT"
    }
}
