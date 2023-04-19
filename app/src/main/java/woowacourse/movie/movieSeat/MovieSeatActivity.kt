package woowacourse.movie.movieSeat

import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.children
import movie.discountpolicy.NormalDiscountPolicy
import movie.seat.Seat
import movie.seat.SeatColumn
import movie.seat.SeatRow
import woowacourse.movie.R
import woowacourse.movie.movieTicket.MovieTicketActivity
import woowacourse.movie.uimodel.MovieTicketUi

class MovieSeatActivity : AppCompatActivity() {

    private val ticketUi by lazy { intent.getSerializableExtra(MovieTicketActivity.KEY_MOVIE_TICKET) as MovieTicketUi }
    private val selectedSeats: MutableList<Seat> = mutableListOf()
    private var totalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)

        val movieTitleView = findViewById<TextView>(R.id.seat_movie_title)
        movieTitleView.text = ticketUi.title

        val seatTableLayout = findViewById<TableLayout>(R.id.seat_table)
        val priceTextView = findViewById<TextView>(R.id.seat_ticket_price)
        val seats = seatTableLayout.children.filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }.toList()

        seats.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, textView ->
                textView.setOnClickListener {
                    val discountPolicy = NormalDiscountPolicy(ticketUi.date, ticketUi.time)

                    val seat = Seat(SeatRow.of(rowIndex), SeatColumn.of(columnIndex))
                    if (!selectedSeats.contains(seat)) {
                        selectedSeats.add(seat)
                        totalPrice += discountPolicy.getDiscountPrice(seat.getSeatPrice())
                        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_background))
                    } else {
                        selectedSeats.remove(seat)
                        totalPrice -= discountPolicy.getDiscountPrice(seat.getSeatPrice())
                        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    }
                    priceTextView.text = getString(R.string.total_price).format(totalPrice)

                    Log.d("krrong", "${rowIndex}행 ${columnIndex}열 클릭됨")
                    Log.d("krrong", selectedSeats.size.toString())
                }
            }
        }
    }
}
