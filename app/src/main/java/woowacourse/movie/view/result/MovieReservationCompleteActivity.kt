package woowacourse.movie.view.result

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.parcelableExtraCompat
import woowacourse.movie.view.reservation.model.TicketUiModel
import woowacourse.movie.view.seat.model.toUiModel
import java.time.format.DateTimeFormatter

class MovieReservationCompleteActivity :
    AppCompatActivity(),
    MovieReservationCompleteContract.View {
    private lateinit var presenter: MovieReservationCompletePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ticket =
            intent.parcelableExtraCompat(EXTRA_TICKET, TicketUiModel::class.java)
                ?: finish().run { return }
        presenter = MovieReservationCompletePresenter(this, ticket)
        presenter.loadMovieReservationCompleteScreen()
    }

    override fun showTicketInfo(ticket: TicketUiModel) {
        findViewById<TextView>(R.id.movie_title).text = ticket.movie.title
        findViewById<TextView>(R.id.showtime).text = ticket.showtime.format(DATE_FORMAT)
        findViewById<TextView>(R.id.ticket_info).text =
            getString(
                R.string.ticket_info,
                ticket.count,
                ticket.seats.seats
                    .map { it.toUiModel() }
                    .joinToString(),
            )
    }

    override fun showTotalPrice(totalPrice: Int) {
        findViewById<TextView>(R.id.total_price).text = getString(R.string.result_price, totalPrice)
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: TicketUiModel,
        ): Intent =
            Intent(context, MovieReservationCompleteActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val EXTRA_TICKET = "extra_ticket"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
    }
}
