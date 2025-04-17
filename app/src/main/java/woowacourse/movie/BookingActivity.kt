package woowacourse.movie

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_DAY
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_MONTH
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_END_YEAR
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_POSTER
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_RUNNING_TIME
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_DAY
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_MONTH
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_START_YEAR
import woowacourse.movie.MainActivity.Companion.KEY_MOVIE_TITLE
import woowacourse.movie.StringFormatter.dotDateFormat
import woowacourse.movie.domain.model.PeopleCount
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.ScreeningTime
import woowacourse.movie.domain.model.TicketType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.max

class BookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)

        val startDateYear = intent.getIntExtra(KEY_MOVIE_START_YEAR, DEFAULT_DATE_YEAR)
        val startDateMonth = intent.getIntExtra(KEY_MOVIE_START_MONTH, DEFAULT_DATE_MONTH)
        val startDateDay = intent.getIntExtra(KEY_MOVIE_START_DAY, DEFAULT_DATE_DAY)

        val endDateYear = intent.getIntExtra(KEY_MOVIE_END_YEAR, DEFAULT_DATE_YEAR)
        val endDateMonth = intent.getIntExtra(KEY_MOVIE_END_MONTH, DEFAULT_DATE_MONTH)
        val endDateDay = intent.getIntExtra(KEY_MOVIE_END_DAY, DEFAULT_DATE_DAY)

        val startDate = LocalDate.of(startDateYear, startDateMonth, startDateDay)
        val endDate = LocalDate.of(endDateYear, endDateMonth, endDateDay)

        initView(startDate, endDate)
    }

    private fun initView(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = intent.getStringExtra(KEY_MOVIE_TITLE)

        val moviePosterView = findViewById<ImageView>(R.id.img_movie_poster)
        moviePosterView.setImageResource(intent.getIntExtra(KEY_MOVIE_POSTER, 0))

        val movieReleaseDateView = findViewById<TextView>(R.id.tv_screening_period)
        movieReleaseDateView.text =
            getString(R.string.text_date_period).format(
                dotDateFormat(startDate),
                dotDateFormat(endDate),
            )

        val movieRunningTimeView = findViewById<TextView>(R.id.tv_running_time)
        movieRunningTimeView.text = intent.getStringExtra(KEY_MOVIE_RUNNING_TIME)

        setDateSpinner(startDate, endDate)
        setButtonListener()
    }

    private fun setDateSpinner(
        startDate: LocalDate,
        endDate: LocalDate,
    ) {
        val dateSpinner = findViewById<Spinner>(R.id.sp_date)

        val screeningBookingDates: List<LocalDate> =
            ScreeningDate(startDate, endDate).bookingDates(LocalDate.now())

        dateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningBookingDates,
            )

        dateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    setTimeSpinner(screeningBookingDates[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    private fun setTimeSpinner(selectedDate: LocalDate) {
        val timeSpinner: Spinner = findViewById(R.id.sp_time)

        val screeningTimes: List<LocalTime> =
            ScreeningTime().getAvailableScreeningTimes(LocalDateTime.now(), selectedDate)

        timeSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningTimes,
            )
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
            val count = max(1, peopleCount.text.toString().toInt() - 1)
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
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(getString(R.string.text_booking_dialog_positive_button)) { _, _ ->
                moveToBookingCompleteActivity()
            }
            .setNegativeButton(getString(R.string.text_booking_dialog_negative_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun moveToBookingCompleteActivity() {
        val title = findViewById<TextView>(R.id.tv_title).text
        val date = findViewById<Spinner>(R.id.sp_date).selectedItem
        val time = findViewById<Spinner>(R.id.sp_time).selectedItem
        val count = findViewById<TextView>(R.id.tv_people_count).text.toString().toInt()
        val price = PeopleCount(count).ticketPrice(TicketType.GENERAL)

        val intent =
            Intent(this, BookingCompleteActivity::class.java).apply {
                putExtra(KEY_BOOKING_MOVIE_TITLE, title)
                putExtra(KEY_BOOKING_DATE_TIME, "$date $time")
                putExtra(KEY_BOOKING_PEOPLE_COUNT, count)
                putExtra(KEY_BOOKING_TICKET_PRICE, price)
            }

        startActivity(intent)
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

    companion object {
        private const val DEFAULT_DATE_YEAR = 1
        private const val DEFAULT_DATE_MONTH = 1
        private const val DEFAULT_DATE_DAY = 1
        const val KEY_BOOKING_MOVIE_TITLE = "BOOKING_MOVIE_TITLE"
        const val KEY_BOOKING_DATE_TIME = "BOOKING_DATE_TIME"
        const val KEY_BOOKING_PEOPLE_COUNT = "BOOKING_PEOPLE_COUNT"
        const val KEY_BOOKING_TICKET_PRICE = "BOOKING_TICKET_PRICE"
    }
}
