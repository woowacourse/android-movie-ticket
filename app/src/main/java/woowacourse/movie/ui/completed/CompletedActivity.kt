package woowacourse.movie.ui.completed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.MovieRepository
import woowacourse.movie.R
import woowacourse.movie.Ticket
import woowacourse.movie.formatScreenDateTime
import woowacourse.movie.util.getSerializable

class CompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)

        val ticket: Ticket = getTicket()
        initView(ticket)
    }

    private fun getTicket(): Ticket = intent.getSerializable(TICKET, Ticket::class.java)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(ticket: Ticket) {
        val movie = MovieRepository.getMovie(ticket.movieId)
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            ticket.bookedDateTime.formatScreenDateTime()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.normal_ticket_count, ticket.count)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount, ticket.getPaymentAmount())
        showBackButton()
    }

    private fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val TICKET = "TICKET"

        fun getIntent(context: Context, ticket: Ticket): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(TICKET, ticket)
            }
        }
    }
}
