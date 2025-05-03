package woowacourse.movie.view.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.StringFormatter
import woowacourse.movie.common.getParcelableExtraCompat
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.seat.model.toUiModel

class ReservationResultActivity :
    AppCompatActivity(),
    ReservationResultContract.View {
    private lateinit var presenter: ReservationResultPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()

        val ticket =
            intent.getParcelableExtraCompat(EXTRA_TICKET, TicketUiModel::class.java)
                ?: finish().run { return }
        presenter = ReservationResultPresenter(this, ticket)
        presenter.loadReservationResultScreen()
    }

    override fun showTicketInfo(ticket: TicketUiModel) {
        findViewById<TextView>(R.id.movie_title).text = ticket.movie.title
        findViewById<TextView>(R.id.showtime).text = StringFormatter.dateTime(ticket.showtime)
        findViewById<TextView>(R.id.ticket_info).text =
            getString(
                R.string.ticket_info,
                ticket.count,
                ticket.seats.seats
                    .map { it.toUiModel() }
                    .joinToString(),
            )
    }

    override fun showTotalPrice(totalPrice: Int) {
        findViewById<TextView>(R.id.total_price).text = getString(R.string.result_price, totalPrice)
    }

    private fun setUpView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: TicketUiModel,
        ): Intent =
            Intent(context, ReservationResultActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val EXTRA_TICKET = "extra_ticket"
    }
}
