package woowacourse.movie.view.booking

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.ext.getSerializable
import woowacourse.movie.view.seat.SeatActivity.Companion.KEY_BOOKING_DATE
import woowacourse.movie.view.seat.SeatActivity.Companion.KEY_BOOKING_MOVIE_TITLE
import woowacourse.movie.view.seat.SeatActivity.Companion.KEY_BOOKING_PEOPLE_COUNT
import woowacourse.movie.view.seat.SeatActivity.Companion.KEY_BOOKING_PRICE
import woowacourse.movie.view.seat.SeatActivity.Companion.KEY_BOOKING_SEAT
import woowacourse.movie.view.seat.SeatActivity.Companion.KEY_BOOKING_TIME
import java.time.LocalDate
import java.time.LocalTime

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)

        val title = intent.getStringExtra(KEY_BOOKING_MOVIE_TITLE) ?: EMPTY_TITLE
        val bookingDate = intent.getSerializable(KEY_BOOKING_DATE, LocalDate::class.java)
        val bookingTime = intent.getSerializable(KEY_BOOKING_TIME, LocalTime::class.java)
        val peopleCount = intent.getIntExtra(KEY_BOOKING_PEOPLE_COUNT, EMPTY_PRICE)
        val seats = intent.getStringExtra(KEY_BOOKING_SEAT) ?: EMPTY_SEATS
        val price = intent.getIntExtra(KEY_BOOKING_PRICE, EMPTY_PRICE)

        initView(title, bookingDate, bookingTime, peopleCount, seats, price)
    }

    private fun initView(
        title: String,
        bookingDate: LocalDate,
        bookingTime: LocalTime,
        peopleCount: Int,
        seats: String,
        ticketPrice: Int,
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initBookingMovieTitleView(title)
        initBookingScheduleView(bookingDate, bookingTime)
        initBookingSeatView(seats)
        initBookingPeopleCountView(peopleCount)
        initBookingTicketPriceView(ticketPrice)
    }

    private fun initBookingMovieTitleView(title: String) {
        val movieTitleView = findViewById<TextView>(R.id.tv_title)
        movieTitleView.text = title
    }

    private fun initBookingScheduleView(
        bookingDate: LocalDate,
        bookingTime: LocalTime,
    ) {
        val formattedBookingDate = StringFormatter.dotDateFormat(bookingDate)
        val scheduleFormat =
            getString(R.string.text_booking_schedule).format(formattedBookingDate, bookingTime)

        findViewById<TextView>(R.id.tv_schedule).text = scheduleFormat
    }

    private fun initBookingSeatView(seats: String) {
        findViewById<TextView>(R.id.tv_seat).text = seats
    }

    private fun initBookingPeopleCountView(peopleCount: Int) {
        findViewById<TextView>(R.id.tv_people_count).text =
            getString(R.string.text_general_people_count).format(peopleCount)
    }

    private fun initBookingTicketPriceView(ticketPrice: Int) {
        val priceFormat = StringFormatter.thousandFormat(ticketPrice)
        findViewById<TextView>(R.id.tv_price).text =
            getString(R.string.text_on_site_payment).format(priceFormat)
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
        private const val EMPTY_TITLE = ""
        private const val EMPTY_SEATS = ""
        private const val EMPTY_PRICE = 0
    }
}
