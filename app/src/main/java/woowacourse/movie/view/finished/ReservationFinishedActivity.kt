package woowacourse.movie.view.finished

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.seats.Seats
import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.presenter.finished.ReservationFinishedContract
import woowacourse.movie.presenter.finished.ReservationFinishedPresenter
import woowacourse.movie.utils.MovieUtils.convertAmountFormat
import woowacourse.movie.utils.MovieUtils.intentSerializable
import woowacourse.movie.view.home.ReservationHomeActivity
import woowacourse.movie.view.home.ReservationHomeActivity.Companion.MOVIE_ID
import woowacourse.movie.view.reservation.ReservationDetailActivity.Companion.DEFAULT_MOVIE_ID
import woowacourse.movie.view.reservation.ReservationDetailActivity.Companion.TICKET
import woowacourse.movie.view.reservation.SeatSelectionActivity.Companion.SEATS

class ReservationFinishedActivity : AppCompatActivity(), ReservationFinishedContract.View {
    private val presenter: ReservationFinishedPresenter = ReservationFinishedPresenter(this, ScreeningDao())

    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_finished_title) }
    private val seatsNumber: TextView by lazy { findViewById(R.id.text_view_reservation_finished_seats) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_date) }
    private val screeningTime: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_time) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_finished_number_of_tickets) }
    private val ticketPrice: TextView by lazy { findViewById(R.id.text_view_reservation_finished_ticket_price) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        handleBackPressed()

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val ticket = intent.intentSerializable(TICKET, Ticket::class.java) ?: Ticket()
        val seats = intent.intentSerializable(SEATS, Seats::class.java) ?: Seats()

        with(presenter) {
            loadMovie(movieId)
            loadTicket(ticket, seats)
        }
    }

    override fun showMovieInformation(movie: Movie) {
        title.text = movie.title
    }

    override fun showReservationHistory(
        ticket: Ticket,
        seats: Seats,
    ) {
        numberOfTickets.text = ticket.count.toString()
        ticketPrice.text = convertAmountFormat(this, ticket.calculateAmount(seats))
        seatsNumber.text =
            seats.seats.joinToString(getString(R.string.reservation_finished_seat_separator)) { "${it.row}${it.column}" }
        screeningDate.text = ticket.screeningDateTime.date
        screeningTime.text = ticket.screeningDateTime.time
    }

    private fun handleBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@ReservationFinishedActivity, ReservationHomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}
