package woowacourse.movie.ui.activity

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
        intent.getParcelable<MovieTicketModel>("ticket")?.let { ticketModel ->
            findViewById<TextView>(R.id.ticket_title).text = ticketModel.title
            findViewById<TextView>(R.id.ticket_date).text = ticketModel.time.format()
            findViewById<TextView>(R.id.ticket_reserved_seats).text =
                getString(
                    R.string.reserved_seat,
                    ticketModel.peopleCount.count,
                    ticketModel.seats.sortedBy { seat -> seat.format() }
                        .joinToString(", ") { seat ->
                            seat.format()
                        }
                )
            findViewById<TextView>(R.id.ticket_price).text = ticketModel.price.format()
        }
    }

    private fun TicketTimeModel.format(): String =
        dateTime.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))

    private fun SeatModel.format(): String = getString(R.string.seat, row.letter, column.value)

    private fun PriceModel.format(): String = getString(R.string.price, amount)
}
