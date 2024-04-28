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
    private val titleTextView: TextView by lazy { findViewById(R.id.text_view_reservation_finished_title) }
    private val screeningScheduleTextView: TextView by lazy { findViewById(R.id.text_view_reservation_finished_screening_schedule) }

    private val seatInformationTextView: TextView by lazy { findViewById(R.id.text_view_reservation_finished_seat_information) }
    private val ticketPriceTextView: TextView by lazy { findViewById(R.id.text_view_reservation_finished_ticket_price) }

    private lateinit var presenter: ReservationFinishedPresenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_finished)

        val movieId = intent.getIntExtra(MOVIE_ID, 0)
        val ticket =
            intent.getSerializableExtra(TICKET, Ticket::class.java)
                ?: throw IllegalArgumentException("빈 티켓이 넘어 왔습니다.")
        val seats = intent.getStringExtra(SEATS) ?: ""
        val totalPrice = intent.getIntExtra(TOTAL_PRICE, 0)

        presenter =
            ReservationFinishedPresenter(
                this,
                movieId,
                ticket,
                seats,
                totalPrice,
            )
        presenter.loadReservationInformation()
    }

    override fun showReservationInformation(
        movieTitle: String,
        screeningDate: String,
        screeningTime: String,
        people: Int,
        seats: String,
        totalPrice: Int,
    ) {
        titleTextView.text = movieTitle
        screeningScheduleTextView.text =
            getString(R.string.reservation_finished_schedule, screeningDate, screeningTime)
        seatInformationTextView.text =
            getString(R.string.reservation_finished_person, people, seats)
        ticketPriceTextView.text =
            getString(R.string.reservation_finished_price, convertPriceFormat(totalPrice))
    }

    private fun convertPriceFormat(price: Int): String {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(price)
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val TICKET = "ticket"
        private const val SEATS = "seats"
        private const val TOTAL_PRICE = "totalPrice"

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
