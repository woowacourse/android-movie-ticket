package woowacourse.movie.ui.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.ui.getParcelable
import woowacourse.movie.ui.model.MovieTicketModel
import woowacourse.movie.ui.model.PriceModel
import woowacourse.movie.ui.model.TicketTimeModel
import woowacourse.movie.ui.model.seat.SeatModel
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTicketInfo()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        setContentView(R.layout.activity_movie_ticket)
        super.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTicketInfo() {
        val titleView: TextView by lazy { findViewById(R.id.ticket_title) }
        val dateView: TextView by lazy { findViewById(R.id.ticket_date) }
        val reservedSeatsView: TextView by lazy { findViewById(R.id.ticket_reserved_seats) }
        val priceView: TextView by lazy { findViewById(R.id.ticket_price) }

        intent.getParcelable<MovieTicketModel>(TICKET_EXTRA_KEY)?.let { ticketModel ->
            titleView.text = ticketModel.title
            dateView.text = ticketModel.time.format()
            reservedSeatsView.text =
                getString(
                    R.string.reserved_seat,
                    ticketModel.peopleCount.count,
                    ticketModel.seats.sortedBy { seat -> seat.format() }
                        .joinToString(", ") { seat ->
                            seat.format()
                        }
                )
            priceView.text = ticketModel.price.format()
        }
    }

    private fun TicketTimeModel.format(): String =
        dateTime.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))

    private fun SeatModel.format(): String = getString(R.string.seat, row.letter, column.value)

    private fun PriceModel.format(): String = getString(R.string.price, amount)

    companion object {
        private const val TICKET_EXTRA_KEY = "ticket"

        fun createIntent(context: Context, ticket: MovieTicketModel): Intent {
            val intent = Intent(context, MovieTicketActivity::class.java)
            intent.putExtra(TICKET_EXTRA_KEY, ticket)
            return intent
        }
    }
}
