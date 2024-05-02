package woowacourse.movie.feature.completed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.feature.reservation.ui.TicketModel
import woowacourse.movie.feature.util.DECIMAL_FORMAT
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity(), ReservationCompletedContract.View {
    private val presenter = ReservationCompletedPresenter(this)
    private val movieTitleTv by lazy { findViewById<TextView>(R.id.completed_movie_title) }
    private val reservationDateTv by lazy { findViewById<TextView>(R.id.completed_reservation_date) }
    private val quantityTv by lazy { findViewById<TextView>(R.id.completed_quantity) }
    private val priceTv by lazy { findViewById<TextView>(R.id.completed_price) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_completed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val id = getReservationId()
        presenter.fetchReservationDetails(id)
    }

    private fun getReservationId(): Long = intent.getLongExtra(TICKET_ID, -1L)

    override fun initializeReservationDetails(ticket: TicketModel) {
        movieTitleTv.text = ticket.title
        reservationDateTv.text = "${ticket.date} ${ticket.time}"
        quantityTv.text = formatSeat(this, ticket.seats)
        priceTv.text = formatPrice(this, ticket.price)
    }

    private fun formatSeat(
        context: Context,
        seats: List<String>,
    ) = String.format(
        context.getString(R.string.reservation_quantity),
        seats.size,
        seats.joinToString(", "),
    )

    private fun formatPrice(
        context: Context,
        price: Long,
    ): String {
        val formattedPrice = DecimalFormat(DECIMAL_FORMAT).format(price)
        return String.format(context.getString(R.string.reservation_price), formattedPrice)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TICKET_ID = "ticket_id"

        fun getIntent(
            context: Context,
            id: Long,
        ): Intent {
            return Intent(context, ReservationCompletedActivity::class.java).apply {
                putExtra(TICKET_ID, id)
            }
        }
    }
}
