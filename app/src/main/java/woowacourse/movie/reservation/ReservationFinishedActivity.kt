package woowacourse.movie.reservation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Ticket
import java.text.DecimalFormat

class ReservationFinishedActivity : AppCompatActivity(), ReservationFinishedContract.View {
    private val title: TextView by lazy { findViewById(R.id.text_view_reservation_finished_title) }
    private val screeningSchedule: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_schedule) }
    private val seatInformation: TextView by lazy { findViewById(R.id.text_view_reservation_finished_seat_information) }
    private val ticketPrice: TextView by lazy { findViewById(R.id.text_view_reservation_finished_ticket_price) }

    private lateinit var presenter: ReservationFinishedPresenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        val movieId = intent.getIntExtra(MOVIE_ID, MOVIE_ID_DEFAULT_VALUE)
        val ticket =
            intent.getSerializableExtra(TICKET, Ticket::class.java)
                ?: throw IllegalArgumentException("빈 티켓이 넘어 왔습니다.")
        val seats = intent.getStringExtra(SEATS) ?: throw IllegalArgumentException("빈 자리가 넘어 왔습니다.")
        val totalPrice = intent.getIntExtra(TOTAL_PRICE, TOTAL_PRICE_DEFAULT_VALUE)

        presenter =
            ReservationFinishedPresenter(
                this,
                movieId,
                ticket,
                seats,
                totalPrice,
            )
    }

    override fun showReservationInformation(
        movieTitle: String,
        screeningDate: String,
        screeningTime: String,
        people: Int,
        seats: String,
        totalPrice: Int,
    ) {
        title.text = movieTitle
        screeningSchedule.text =
            getString(R.string.reservation_finished_schedule, screeningDate, screeningTime)
        seatInformation.text =
            getString(R.string.reservation_finished_person, people, seats)
        ticketPrice.text =
            getString(R.string.reservation_finished_price, convertPriceFormat(totalPrice))
    }

    private fun convertPriceFormat(price: Int): String {
        val decimalFormat = DecimalFormat(PRICE_FORMAT)
        return decimalFormat.format(price)
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val TICKET = "ticket"
        private const val SEATS = "seats"
        private const val TOTAL_PRICE = "totalPrice"
        private const val MOVIE_ID_DEFAULT_VALUE = 0
        private const val TOTAL_PRICE_DEFAULT_VALUE = 0
        private const val PRICE_FORMAT = "#,###"

        fun getIntent(
            context: Context,
            movieId: Int,
            ticket: Ticket,
            seats: String,
            totalPrice: Int,
        ): Intent {
            return Intent(context, ReservationFinishedActivity::class.java)
                .putExtra(MOVIE_ID, movieId)
                .putExtra(TICKET, ticket)
                .putExtra(SEATS, seats)
                .putExtra(TOTAL_PRICE, totalPrice)
        }
    }
}
