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
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel
import woowacourse.movie.view.utils.getParcelableCompat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MovieReservationResultActivity : AppCompatActivity(), MovieReservationResultContract.View {
    private val presenter = MovieReservationResultPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        val ticket = intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET) ?: return
        val theater = intent.extras?.getParcelableCompat<TheaterUiModel>(KEY_THEATER) ?: return
        presenter.onViewCreated(ticket, theater)
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showMovieTitle(title: String) {
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = title
    }

    override fun showMovieDateTime(showtime: LocalDateTime) {
        val showtimeTextView = findViewById<TextView>(R.id.showtime)
        showtimeTextView.text = DateTimeFormatter.ofPattern(getString(R.string.format_date_time)).format(showtime)
    }

    override fun showTicketCount(count: Int) {
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)
        ticketCountTextView.text = getString(R.string.template_ticket_count).format(count)
    }

    override fun showSelectedSeats(seats: String) {
        val selectedSeatsTextView = findViewById<TextView>(R.id.selected_seats)
        selectedSeatsTextView.text = seats
    }

    override fun showTotalPrice(price: Int) {
        val totalPriceTextView = findViewById<TextView>(R.id.total_price)
        totalPriceTextView.text = getString(R.string.template_price).format(price)
    }

    companion object {
        const val KEY_TICKET = "ticket"
        const val KEY_THEATER = "theater"

        fun createIntent(
            context: Context,
            ticket: TicketUiModel,
            theater: TheaterUiModel,
        ): Intent {
            val intent = Intent(context, MovieReservationResultActivity::class.java)
            intent.putExtra(KEY_TICKET, ticket)
            intent.putExtra(KEY_THEATER, theater)
            return intent
        }
    }
}
