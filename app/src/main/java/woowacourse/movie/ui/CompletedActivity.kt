package woowacourse.movie.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieData
import woowacourse.movie.domain.Ticket
import woowacourse.movie.formatScreenDateTime

class CompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)

        val ticket: Ticket = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(TICKET, Ticket::class.java)
                ?: throw IllegalArgumentException()
        } else {
            intent.getParcelableExtra(TICKET) ?: throw IllegalArgumentException()
        }
        initView(ticket)
    }

    private fun initView(ticket: Ticket) {
        val movie = MovieData.findMovieById(ticket.movieId)
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            ticket.bookedDateTime.formatScreenDateTime()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.normal_ticket_count).format(ticket.count)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount).format(getPaymentAmount(ticket.count)) // format(DecimalFormat("#,##0").format(getPaymentAmount(ticketCount)))
    }

    private fun getPaymentAmount(ticketCount: Int) = ticketCount * TICKET_PRICE

    companion object {
        private const val TICKET = "TICKET"
        private const val TICKET_PRICE = 13000

        fun getIntent(context: Context, ticket: Ticket): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(TICKET, ticket)
            }
        }
    }
}
