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
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.TicketOfficeUiModel
import woowacourse.movie.view.model.TicketsUiModel
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
    private val ticketOfficeUiModel: TicketOfficeUiModel by lazy {
        receiveTicketOfficeViewModel() ?: run {
            finishActivityWithMessage(getString(R.string.reservation_data_null_error))
            TicketOfficeUiModel(TicketsUiModel(listOf()), 0)
        }
    }

    private val movieUiModel: MovieUiModel by lazy {
        receiveMovieViewModel() ?: run {
            finishActivityWithMessage(getString(R.string.reservation_data_null_error))
            MovieUiModel(0, "qwe", LocalDate.MAX, LocalDate.MAX, 0, "")
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
        MovieView(title = movieTitleTextView).render(movieUiModel)
    }

    private fun renderReservationDetailView() {
        val ticketOffice = TicketOfficeMapper.toDomain(ticketOfficeUiModel)
        renderDate()
        renderPeopleCount()
        renderSeatInformation()
        renderPrice(ticketOffice)
    }

    private fun renderDate() {
        val dateFormat =
            DateTimeFormatter.ofPattern(getString(R.string.reservation_datetime_format))
        dateTextView.text = dateFormat.format(ticketOfficeUiModel.ticketsUiModel.list[0].date)
    }

    private fun renderPeopleCount() {
        peopleCountTextView.text =
            getString(R.string.reservation_people_count).format(ticketOfficeUiModel.ticketCount)
    }

    private fun renderSeatInformation() {
        ticketOfficeUiModel.ticketsUiModel.list.forEachIndexed { index, ticket ->
            seatTextView.text =
                (seatTextView.text.toString() + ticket.seat.row + ticket.seat.col)
            if (index != ticketOfficeUiModel.ticketCount - 1) seatTextView.text =
                seatTextView.text.toString() + ", "
        }
    }

    private fun renderPrice(ticketOffice: TicketOffice) {
        val formattedPrice =
            NumberFormat.getNumberInstance(Locale.US).format(ticketOffice.tickets.price.value)
        priceTextView.text = getString(R.string.reservation_price).format(formattedPrice)
    }

    private fun receiveTicketOfficeViewModel(): TicketOfficeUiModel? {
        return intent.extras?.getSerializableCompat(TICKET_OFFICE_KEY_VALUE)
    }

    private fun receiveMovieViewModel(): MovieUiModel? {
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
        fun start(
            context: Context,
            movieUiModel: MovieUiModel,
            ticketOfficeUiModel: TicketOfficeUiModel
        ) {
            val intent = Intent(context, ReservationResultActivity::class.java)
            intent.putExtra(MOVIE_KEY_VALUE, movieUiModel)
            intent.putExtra(TICKET_OFFICE_KEY_VALUE, ticketOfficeUiModel)
            context.startActivity(intent)
        }
    }
}
