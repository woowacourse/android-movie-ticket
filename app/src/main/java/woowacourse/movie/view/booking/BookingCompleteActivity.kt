package woowacourse.movie.view.booking

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.Booking
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING
import woowacourse.movie.view.ext.getSerializable
import java.time.LocalDate
import java.time.LocalTime

class BookingCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)

        intent.getSerializable(KEY_BOOKING, Booking::class.java)?.let {
            initView(it)
        }
    }

    private fun initView(booking: Booking) {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initBookingMovieTitleView(booking.title)
        initBookingScheduleView(booking.bookingDate, booking.bookingTime)
        initBookingPeopleCountView(booking.count.value)
        initBookingTicketPriceView(booking.ticketPrice)
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
}
