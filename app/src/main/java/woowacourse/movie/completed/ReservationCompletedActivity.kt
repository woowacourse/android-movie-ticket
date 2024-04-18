package woowacourse.movie.completed

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Ticket
import woowacourse.movie.utils.FormatUtils

class ReservationCompletedActivity : AppCompatActivity(), ReservationCompletedContract.View {
    private val presenter = ReservationCompletedPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.onViewCreated(intent)
    }

    override fun initializeTicketDetails(ticket: Ticket) {
        findViewById<TextView>(R.id.movie_title).text = ticket.getTitle()
        findViewById<TextView>(R.id.opening_day).text =
            getString(R.string.movie_opening_day, ticket.getOpeningDay())
        findViewById<TextView>(R.id.quantity).text =
            getString(R.string.ticket_quantity, ticket.quantity)
        findViewById<TextView>(R.id.price).text =
            getString(
                R.string.ticket_price,
                FormatUtils.formatCurrency(ticket.getPrice() * ticket.quantity),
            )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
