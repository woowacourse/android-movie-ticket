package woowacourse.movie.activity.bookingresult

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.activity.booking.BookingActivity
import woowacourse.movie.domain.Ticket
import woowacourse.movie.ui.TicketUiModel

class BookingResultActivity :
    AppCompatActivity(),
    BookingResultContract.View {
    private lateinit var presenter: BookingResultContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)

        presenter = BookingResultPresenter()
        presenter.attachView(this)

        val ticket = intent.getParcelableExtra<Ticket>(BookingActivity.Companion.KEY_TICKET)

        if (ticket != null) {
            presenter.loadTicket(ticket)
        }
    }

    override fun showTicketInfo(ticket: Ticket) {
        val ticketUiModel = TicketUiModel.fromDomain(ticket)

        findViewById<TextView>(R.id.title).text = ticketUiModel.title
        findViewById<TextView>(R.id.date).text = ticketUiModel.date
        findViewById<TextView>(R.id.time).text = ticketUiModel.time
        findViewById<TextView>(R.id.count).text =
            String.format(
                resources.getString(R.string.people_count),
                ticketUiModel.count,
            )
        findViewById<TextView>(R.id.money).text =
            String.format(
                resources.getString(R.string.payment),
                ticketUiModel.money,
            )
        findViewById<TextView>(R.id.seat).text = ticketUiModel.seat
    }
}
