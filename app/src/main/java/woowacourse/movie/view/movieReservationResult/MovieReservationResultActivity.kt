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

class MovieReservationResultActivity : AppCompatActivity(), MovieReservationResultContract.View {
    private val presenter = MovieReservationResultPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        presenter.loadReservationInfo()
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

    override fun showMovieDateTime(showtime: String) {
        val showtimeTextView = findViewById<TextView>(R.id.showtime)
        showtimeTextView.text = showtime
    }

    override fun showTicketCount(count: String) {
        val ticketCountTextView = findViewById<TextView>(R.id.ticket_count)
        ticketCountTextView.text = count
    }

    override fun showSelectedSeats(seats: String) {
        val selectedSeatsTextView = findViewById<TextView>(R.id.selected_seats)
        selectedSeatsTextView.text = seats
    }

    override fun showTotalPrice(price: String) {
        val totalPriceTextView = findViewById<TextView>(R.id.total_price)
        totalPriceTextView.text = price
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
