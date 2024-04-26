package woowacourse.movie.presentation.ticketingResult

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.seatSelection.SeatSelectionActivity.Companion.EXTRA_MOVIE_TICKET
import woowacourse.movie.utils.formatSeat

class TicketingResultActivity : AppCompatActivity(), TicketingResultContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movieTicket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(EXTRA_MOVIE_TICKET, Ticket::class.java)
            } else {
                intent.getSerializableExtra(EXTRA_MOVIE_TICKET) as Ticket
            }
        val ticketingResultPresenter = TicketingResultPresenter(this, movieTicket)
        ticketingResultPresenter.assignInitialView()
    }

    override fun assignInitialView(
        movieTitle: String,
        movieDateTime: String,
        ticketCount: Int,
        selectedSeats: List<Seat>,
        totalPrice: Int,
    ) {
        val movieTitleText = findViewById<TextView>(R.id.tv_movie_title)
        val movieDateText = findViewById<TextView>(R.id.tv_movie_date)
        val ticketCountText = findViewById<TextView>(R.id.tv_ticket_count)
        val selectedSeatsText = findViewById<TextView>(R.id.tv_selected_seats)
        val priceText = findViewById<TextView>(R.id.tv_price)

        movieTitleText.text = movieTitle
        movieDateText.text = movieDateTime
        ticketCountText.text = getString(R.string.text_number_of_people, ticketCount)
        selectedSeatsText.text =
            selectedSeats.joinToString(", ") { formatSeat(it) }
        priceText.text = getString(R.string.text_price, totalPrice)
    }

    override fun showErrorMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
