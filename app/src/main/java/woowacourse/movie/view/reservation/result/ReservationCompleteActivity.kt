package woowacourse.movie.view.reservation.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieseat.Seats
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.view.dialog.DialogFactory
import java.time.format.DateTimeFormatter

class ReservationCompleteActivity : AppCompatActivity(), ReservationCompleteContract.View {
    private val presenter by lazy {
        ReservationCompletePresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root_layout_reservation_complete)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ticket = intent.getSerializableExtraCompat<Ticket>(KEY_TICKET)
        val seats = intent.getSerializableExtraCompat<Seats>(KET_SEATS)

        fetchTicketOrShowError(ticket, seats)
    }

    private fun fetchTicketOrShowError(
        ticket: Ticket?,
        seats: Seats?,
    ) {
        if (ticket == null || seats == null) {
            handleInvalidTicket()
        } else {
            presenter.fetchData(ticket, seats)
        }
    }

    override fun handleInvalidTicket() {
        DialogFactory().showError(this) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun showTicketInfo(
        ticket: Ticket,
        seats: Seats,
    ) {
        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        val dateTimeFormat = ticket.date.format(formatter)

        val movieTitleTextView = findViewById<TextView>(R.id.tv_movie_title)
        val movieCancelInfoTextView = findViewById<TextView>(R.id.tv_cancel_info)
        val movieDateTextView = findViewById<TextView>(R.id.tv_movie_date)
        val moviePersonnel = findViewById<TextView>(R.id.tv_movie_personnel)

        movieTitleTextView.text = ticket.title
        movieCancelInfoTextView.text = getString(R.string.movie_cancel_deadline, Ticket.CANCEL_DEADLINE)
        movieDateTextView.text = dateTimeFormat
        moviePersonnel.text = getString(R.string.moviePersonnel, ticket.personnel)
    }

    override fun showSeatsInfo(seats: String) {
        val movieSeatsTextView = findViewById<TextView>(R.id.tv_seat_list)
        movieSeatsTextView.text = getString(R.string.seat_list, seats)
    }

    override fun showTicketMoney(moviePrice: Int) {
        val priceFormatter = java.text.DecimalFormat(PRICE_PATTERN)
        val movieTotalPrice = findViewById<TextView>(R.id.tv_movie_total_price)
        movieTotalPrice.text = getString(R.string.movieTotalPrice, priceFormatter.format(moviePrice))
    }

    companion object {
        private const val DATETIME_PATTERN = "yyyy.M.d. HH:mm"
        private const val PRICE_PATTERN = "#,###"
        private const val KEY_TICKET = "ticket"
        private const val KET_SEATS = "seats"

        fun newIntent(
            context: Context,
            ticket: Ticket,
            seats: Seats,
        ): Intent =
            Intent(context, ReservationCompleteActivity::class.java)
                .putExtra(KEY_TICKET, ticket)
                .putExtra(KET_SEATS, seats)
    }
}
