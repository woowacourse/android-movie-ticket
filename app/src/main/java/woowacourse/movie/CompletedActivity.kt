package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CompletedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed)

        val movieId = intent.getLongExtra(MOVIE_ID, -1)
        val ticketCount = intent.getIntExtra(TICKET_COUNT, 1)
        initView(MovieData.findMovieById(movieId), ticketCount)
    }

    private fun initView(movie: Movie, ticketCount: Int) {
        findViewById<TextView>(R.id.textCompletedTitle).text = movie.title
        findViewById<TextView>(R.id.textCompletedScreeningDate).text =
            movie.screeningDate.formatScreenDate()
        findViewById<TextView>(R.id.textCompletedTicketCount).text =
            getString(R.string.normal_ticket_count).format(ticketCount)
        findViewById<TextView>(R.id.textCompletedPaymentAmount).text =
            getString(R.string.payment_amount).format(getPaymentAmount(ticketCount)) // format(DecimalFormat("#,##0").format(getPaymentAmount(ticketCount)))
    }

    private fun getPaymentAmount(ticketCount: Int) = ticketCount * TICKET_PRICE

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        private const val TICKET_COUNT = "TICKET_COUNT"
        private const val TICKET_PRICE = 13000

        fun getIntent(context: Context, movieId: Long, ticketCount: Int): Intent {
            return Intent(context, CompletedActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
                putExtra(TICKET_COUNT, ticketCount)
            }
        }
    }
}
