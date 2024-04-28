package woowacourse.movie.reservation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReservationFinishedActivity : AppCompatActivity(), ReservationFinishedContract.View {
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_finished_title) }
    private val screeningDate: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_date) }
    private val numberOfTickets: TextView by lazy { findViewById(R.id.text_view_reservation_finished_number_of_tickets) }
    private val ticketPrice: TextView by lazy { findViewById(R.id.text_view_reservation_finished_ticket_price) }

    private lateinit var presenter: ReservationFinishedPresenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        val movieTitle = intent.getStringExtra(MOVIE_TITLE)
        val ticket = intent.getSerializableExtra(TICKET, Ticket::class.java)
        val seats = intent.getStringExtra(SEATS)
        val totalPrice = intent.getIntExtra(TOTAL_PRICE, 0)
    }

    override fun showMovieInformation(movie: Movie) {
        title.text = movie.title
        screeningDate.text = convertDateFormat(movie.firstScreeningDate)
    }

    override fun showReservationHistory(
        ticketCount: Int,
        price: Int,
    ) {
        numberOfTickets.text = getString(R.string.reservation_finished_person, ticketCount)
        ticketPrice.text = getString(R.string.reservation_finished_price, convertPriceFormat(price))
    }

    private fun convertDateFormat(date: LocalDate): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        return date.format(dateFormat)
    }

    private fun convertPriceFormat(price: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price)
    }

    companion object {
        private const val MOVIE_TITLE = "movieTitle"
        private const val TICKET = "ticket"
        private const val SEATS = "seats"
        private const val TOTAL_PRICE = "totalPrice"

        fun getIntent(
            context: Context,
            movieTitle: String,
            ticket: Ticket,
            seats: String,
            totalPrice: Int,
        ): Intent {
            return Intent(context, ReservationFinishedActivity::class.java)
                .putExtra(MOVIE_TITLE, movieTitle)
                .putExtra(TICKET, ticket)
                .putExtra(SEATS, seats)
                .putExtra(TOTAL_PRICE, totalPrice)
        }
    }
}
