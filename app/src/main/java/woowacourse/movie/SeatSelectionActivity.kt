package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import domain.Seat
import domain.Seats
import woowacourse.movie.dto.MovieDateDto
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieTimeDto
import woowacourse.movie.dto.SeatsDto
import woowacourse.movie.dto.TicketCountDto
import woowacourse.movie.mapper.mapToSeats
import woowacourse.movie.mapper.mapToSeatsDto
import woowacourse.movie.view.SeatSelectView
import java.time.LocalDateTime

class SeatSelectionActivity : AppCompatActivity() {

    private var seats = Seats()

    private val date by lazy { intent.getSerializableExtra(DATE_KEY) as MovieDateDto }
    private val time by lazy { intent.getSerializableExtra(TIME_KEY) as MovieTimeDto }
    private val movie by lazy { intent.getSerializableExtra(MOVIE_KEY) as MovieDto }
    private val ticketCount by lazy { intent.getSerializableExtra(TICKET_KEY) as TicketCountDto }
    private val enterBtn by lazy { findViewById<TextView>(R.id.enterBtn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        setUpState(savedInstanceState)
        setMovieTitle()
    }

    private fun setUpSeatsView() {
        SeatSelectView(
            findViewById(R.id.seat_layout),
            ::onSeatClick,
            seats,
        )
    }

    private fun setMovieTitle() {
        val movieTtile = findViewById<TextView>(R.id.movie_title)
        movieTtile.text = movie.title
    }

    private fun setUpState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val seatsDto = savedInstanceState.getSerializable(SEATS_POSITION) as SeatsDto
            seats = seatsDto.mapToSeats()
            setPrice(seats.caculateSeatPrice(LocalDateTime.of(date.date, time.time)))
        } else {
            setPrice(0)
        }
        setUpSeatsView()
        setEnterBtnClickable()
    }

    private fun onSeatClick(seat: Seat, textView: TextView) {
        when {
            isPossibleSelect(seat, ticketCount.numberOfPeople) -> selectSeat(
                textView,
                seat,
            )
            isSeatCancelable(seat) -> unselectSeat(textView, seat)
            else -> Toast.makeText(this, R.string.seats_size_over_error, Toast.LENGTH_LONG)
                .show()
        }
        setPrice(seats.caculateSeatPrice(LocalDateTime.of(date.date, time.time)))
        setEnterBtnClickable()
    }

    private fun setEnterBtnClickable() {
        if (isPossibleEnter(ticketCount.numberOfPeople)) {
            enterBtn.setBackgroundColor(getColor(R.color.enter))
            OnEnterBtnClickListener(enterBtn)
        } else {
            enterBtn.setBackgroundColor(getColor(R.color.not_enter))
            enterBtn.setOnClickListener(null)
        }
    }

    private fun OnEnterBtnClickListener(enterBtn: TextView) {
        enterBtn.setOnClickListener {
            showBookingDialog()
        }
    }

    private fun showBookingDialog() {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.seat_dialog_title)
            .setMessage(R.string.seat_dialog_message)
            .setPositiveButton(R.string.seat_dialog_yes) { _, _ -> moveActivity() }
            .setNegativeButton(R.string.seat_dialog_no) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun isSeatCancelable(seat: Seat): Boolean {
        return seats.containsSeat(seat)
    }

    private fun isPossibleEnter(count: Int): Boolean {
        return seats.checkSeatSizeMatch(count)
    }

    private fun isPossibleSelect(seat: Seat, count: Int): Boolean {
        return !seats.containsSeat(seat) && seats.isPossibleSeatSize(count)
    }

    private fun selectSeat(textView: TextView, seat: Seat) {
        textView.setBackgroundColor(getColor(R.color.select_seat))
        seats.add(seat)
    }

    private fun unselectSeat(textView: TextView, seat: Seat) {
        textView.setBackgroundColor(getColor(R.color.white))
        seats.remove(seat)
    }

    private fun setPrice(ticketPrice: Int) {
        val price = findViewById<TextView>(R.id.ticket_price)
        price.text = getString(R.string.ticket_price_seat_page, ticketPrice)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(SEATS_POSITION, seats.mapToSeatsDto())
        super.onSaveInstanceState(outState)
    }

    private fun moveActivity() {
        val intent = Intent(this, TicketActivity::class.java)
        intent.putExtra(MOVIE_KEY, movie)
        intent.putExtra(SEATS_KEY, seats.mapToSeatsDto())
        intent.putExtra(DATE_KEY, date)
        intent.putExtra(TIME_KEY, time)
        intent.putExtra(TICKET_KEY, ticketCount)
        startActivity(intent)
    }

    companion object {
        const val TICKET_KEY = "ticket"
        const val MOVIE_KEY = "movie"
        const val DATE_KEY = "movie_date"
        const val TIME_KEY = "movie_time"
        const val SEATS_KEY = "seats"
        private const val SEATS_POSITION = "seats_position"
    }
}
