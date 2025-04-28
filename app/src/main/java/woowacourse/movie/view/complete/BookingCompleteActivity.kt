package woowacourse.movie.view.complete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.view.StringFormatter
import woowacourse.movie.view.ext.getSerializable
import java.time.LocalDate
import java.time.LocalTime

class BookingCompleteActivity : AppCompatActivity(), BookingCompleteContract.View {
    private lateinit var presenter: BookingCompleteContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_complete)

        val ticket = intent.getSerializable(KEY_TICKET, Ticket::class.java)
        presenter = BookingCompletePresenter(this, ticket)

        initView()
    }

    private fun initView() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadTicket()
    }

    override fun showTicket(ticket: Ticket) {
        with(ticket) {
            initBookingMovieTitleView(title)
            initBookingScheduleView(bookingDate, bookingTime)
            initBookingSeatView(seats)
            initBookingPeopleCountView(count.value)
            initBookingTicketPriceView(price)
        }
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

    private fun initBookingSeatView(seats: Set<Seat>) {
        findViewById<TextView>(R.id.tv_seat).text = seatToLabel(seats)
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

    private fun seatToLabel(seats: Set<Seat>): String {
        return seats.joinToString {
            val rowLetter = ('A' + it.x - 1)
            val columnNumber = it.y
            "$rowLetter$columnNumber"
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

    companion object {
        const val KEY_TICKET = "BOOKING_TICKET"

        fun newIntent(
            context: Context,
            ticket: Ticket,
        ) = Intent(context, BookingCompleteActivity::class.java).apply {
            putExtra(KEY_TICKET, ticket)
        }
    }
}
