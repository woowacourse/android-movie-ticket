package woowacourse.movie.view.movieReservationResult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.presenter.movieReservationResult.MovieReservationResultContract
import woowacourse.movie.presenter.movieReservationResult.MovieReservationResultPresenter
import woowacourse.movie.view.model.SeatsUiModel
import woowacourse.movie.view.model.TicketUiModel
import woowacourse.movie.view.model.toDomain
import java.time.format.DateTimeFormatter

class MovieReservationResultActivity : AppCompatActivity(), MovieReservationResultContract.View {
    private val presenter = MovieReservationResultPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        presenter.loadReservationInfo()
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_completion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showReservationInfo(ticket: TicketUiModel) {
        val title = findViewById<TextView>(R.id.movie_title)
        val showtime = findViewById<TextView>(R.id.showtime)
        val ticketCount = findViewById<TextView>(R.id.ticket_count)
        val totalPrice = findViewById<TextView>(R.id.total_price)

        title.text = ticket.movie.title
        showtime.text = ticket.showtime.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))
        ticketCount.text = getString(R.string.ticket_count_format).format(ticket.count)
        totalPrice.text = getString(R.string.ticket_price_format).format(ticket.toDomain().totalPrice())
    }

    companion object {
        const val KEY_TICKET = "ticket"
        const val KEY_SEATS = "seats"

        fun createIntent(
            context: Context,
            ticket: TicketUiModel,
            seats: SeatsUiModel,
        ): Intent {
            val intent = Intent(context, MovieReservationResultActivity::class.java)
            intent.putExtra(KEY_TICKET, ticket)
            intent.putExtra(KEY_SEATS, seats)
            return intent
        }
    }
}
