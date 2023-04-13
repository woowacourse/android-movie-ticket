package woowacourse.movie.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.MovieData
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.discountpolicy.DateTimeTimeDiscountAdapter
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(ticket: Ticket) {
        val movie = MovieData.findMovieById(ticket.movieId)
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            ticket.bookedDateTime.formatScreenDateTime()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.normal_ticket_count).format(ticket.count)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount).format(getPaymentAmount(ticket))
    }

    private fun getPaymentAmount(ticket: Ticket) =
        DateTimeTimeDiscountAdapter(ticket.bookedDateTime).discount(TICKET_PRICE) * ticket.count

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
