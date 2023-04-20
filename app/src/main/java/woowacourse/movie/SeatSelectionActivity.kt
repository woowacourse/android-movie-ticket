package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import domain.Position
import domain.Seat
import domain.Seats
import domain.TicketPrice
import woowacourse.movie.dto.MovieDateDto
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieTimeDto
import woowacourse.movie.dto.SeatsDto
import woowacourse.movie.dto.TicketCountDto
import woowacourse.movie.mapper.mapToSeats
import woowacourse.movie.mapper.mapToSeatsDto
import woowacourse.movie.mapper.mapToUIModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatSelectionActivity : AppCompatActivity() {

    private var seats = Seats()
    private val date by lazy { intent.getSerializableExtra(DATE_KEY) as MovieDateDto }
    private val time by lazy { intent.getSerializableExtra(TIME_KEY) as MovieTimeDto }
    private val movie by lazy { intent.getSerializableExtra(MOVIE_KEY) as MovieDto }
    private val ticketCount by lazy { intent.getSerializableExtra(TICKET_KEY) as TicketCountDto }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        setUpState(savedInstanceState)
        setMovieTitle()

        onSeatClickListener(date.date, time.time)
    }

    private fun setMovieTitle() {
        val movieTtile = findViewById<TextView>(R.id.movie_title)
        movieTtile.text = movie.title
    }

    private fun setUpState(savedInstanceState: Bundle?) {
        setUpSeatView()
        if (savedInstanceState != null) {
            val seatsDto = savedInstanceState.getSerializable(SEATS_POSITION) as SeatsDto
            seats = seatsDto.mapToSeats()
            setUpSelectSeats()
        } else {
            setPrice(0)
        }
    }

    private fun setUpSelectSeats() {
        getSeatView().forEachIndexed { index, textView ->
            val seat = Seat(Position.of(index), TicketPrice.of(Position.of(index)))
            if (seats.containsSeat(seat)) {
                textView.setBackgroundColor(getColor(R.color.select_seat))
            }
        }
        setPrice(seats.caculateSeatPrice(LocalDateTime.of(date.date, time.time)))
    }

    private fun setUpSeatView() {
        getSeatView().forEachIndexed { index, textView ->
            val position = Position.of(index)
            textView.text = position.mapToUIModel().getPosition()
            textView.setTextColor(getColor(position.mapToUIModel().getColor()))
        }
    }

    private fun getSeatView(): List<TextView> {
        return findViewById<TableLayout>(R.id.seat)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()
    }

    private fun onSeatClickListener(date: LocalDate, time: LocalTime) {
        getSeatView().forEachIndexed { index, textView ->
            textView.setOnClickListener {
                val seat = Seat(Position.of(index), TicketPrice.of(Position.of(index)))
                when {
                    isPossibleSelect(seat, ticketCount.numberOfPeople) -> selectSeat(textView, seat)
                    isSeatCancelable(seat) -> unselectSeat(textView, seat)
                    else -> Toast.makeText(this, R.string.seats_size_over_error, Toast.LENGTH_LONG).show()
                }
                setPrice(seats.caculateSeatPrice(LocalDateTime.of(date, time)))
                setEnterBtnClickable()
            }
        }
    }

    private fun setEnterBtnClickable() {
        val enterBtn = findViewById<TextView>(R.id.enterBtn)
        when {
            isPossibleEnter(ticketCount.numberOfPeople) -> {
                enterBtn.setBackgroundColor(getColor(R.color.enter))
                OnEnterBtnClickListener(enterBtn)
            }
            else -> {
                enterBtn.setBackgroundColor(getColor(R.color.not_enter))
                enterBtn.setOnClickListener(null)
            }
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
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예") { _, _ -> moveActivity() }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
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

    private fun isSeatCancelable(seat: Seat): Boolean {
        return seats.containsSeat(seat)
    }

    private fun isPossibleEnter(count: Int): Boolean {
        return seats.checkSeatCountAndSizeMatch(count)
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

    companion object {
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY = "movie"
        private const val DATE_KEY = "movie_date"
        private const val TIME_KEY = "movie_time"
        private const val SEATS_KEY = "seats"
        private const val SEATS_POSITION = "seats_position"
    }
}
