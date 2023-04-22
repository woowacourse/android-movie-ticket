package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.TicketOffice
import woowacourse.movie.R
import woowacourse.movie.getSerializableCompat
import woowacourse.movie.view.MovieView
import woowacourse.movie.view.mapper.TicketOfficeMapper
import woowacourse.movie.view.model.MovieViewModel
import woowacourse.movie.view.model.TicketOfficeViewModel
import woowacourse.movie.view.model.TicketsViewModel
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ReservationResultActivity : AppCompatActivity() {
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_title) }
    private val dateTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_date) }
    private val peopleCountTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_people_count) }
    private val seatTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_seat) }
    private val priceTextView: TextView by lazy { findViewById(R.id.movie_reservation_result_price) }
    private val ticketOfficeViewModel: TicketOfficeViewModel by lazy {
        receiveTicketOfficeViewModel() ?: run {
            finishActivityWithMessage(RESERVATION_DATA_NULL_ERROR)
            TicketOfficeViewModel(TicketsViewModel(listOf()), 0)
        }
    }

    private val movieViewModel: MovieViewModel by lazy {
        receiveMovieViewModel() ?: run {
            finishActivityWithMessage(RESERVATION_DATA_NULL_ERROR)
            MovieViewModel(0, "qwe", LocalDate.MAX, LocalDate.MAX, 0, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_result)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        renderMovieView()
        renderReservationDetailView()
    }

    private fun finishActivityWithMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun renderMovieView() {
        MovieView(title = movieTitleTextView).render(movieViewModel)
    }

    private fun renderReservationDetailView() {
        val ticketOffice = TicketOfficeMapper.toDomain(ticketOfficeViewModel)
        renderDate()
        renderPeopleCount()
        renderSeatInformation()
        renderPrice(ticketOffice)
    }

    private fun renderDate() {
        val dateFormat =
            DateTimeFormatter.ofPattern(getString(R.string.reservation_datetime_format))
        dateTextView.text = dateFormat.format(ticketOfficeViewModel.ticketsViewModel.list[0].date)
    }

    private fun renderPeopleCount() {
        peopleCountTextView.text =
            getString(R.string.reservation_people_count).format(ticketOfficeViewModel.ticketCount)
    }

    private fun renderSeatInformation() {
        ticketOfficeViewModel.ticketsViewModel.list.forEachIndexed { index, ticket ->
            seatTextView.text =
                (seatTextView.text.toString() + ticket.seat.row.name + ticket.seat.col)
            if (index != ticketOfficeViewModel.ticketCount - 1) seatTextView.text =
                seatTextView.text.toString() + ", "
        }
    }

    private fun renderPrice(ticketOffice: TicketOffice) {
        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(ticketOffice.tickets.price.value)
        priceTextView.text = getString(R.string.reservation_price).format(formattedPrice)
    }

    private fun receiveTicketOfficeViewModel(): TicketOfficeViewModel? {
        return intent.extras?.getSerializableCompat(TICKET_OFFICE_KEY_VALUE)
    }

    private fun receiveMovieViewModel(): MovieViewModel? {
        return intent.extras?.getSerializableCompat(MOVIE_KEY_VALUE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val MOVIE_KEY_VALUE = "movie"
        private const val TICKET_OFFICE_KEY_VALUE = "ticket_office"
        private const val RESERVATION_DATA_NULL_ERROR = "예약 정보를 받지 못하였습니다!"
        fun start(
            context: Context,
            movieViewModel: MovieViewModel,
            ticketOfficeViewModel: TicketOfficeViewModel
        ) {
            val intent = Intent(context, ReservationResultActivity::class.java)
            intent.putExtra(MOVIE_KEY_VALUE, movieViewModel)
            intent.putExtra(TICKET_OFFICE_KEY_VALUE, ticketOfficeViewModel)
            context.startActivity(intent)
        }
    }
}
