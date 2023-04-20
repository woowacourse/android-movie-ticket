package woowacourse.movie.movieSeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
import woowacourse.movie.uimodel.MovieDetailUi
import woowacourse.movie.uimodel.MovieTicketUi
import woowacourse.movie.utils.toDomain

class MovieSeatActivity : AppCompatActivity() {

    private val movieDetail by lazy { (intent.getSerializableExtra(KEY_MOVIE_DETAIL) as MovieDetailUi).toDomain() }
    private val selectedSeats: MutableList<Seat> = mutableListOf()
    private var totalPrice: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)

        val movieTitleView = findViewById<TextView>(R.id.seat_movie_title)
        movieTitleView.text = movieDetail.title

        val nextButton = findViewById<Button>(R.id.seat_next_button)
        nextButton.setOnClickListener {
            val movieTicketUi = MovieTicketUi(
                totalPrice = totalPrice,
                count = movieDetail.count,
                title = movieDetail.title,
                date = movieDetail.date,
                time = movieDetail.time,
                seats = selectedSeats.map { it.getSeatPosition() },
            )

            val intent = Intent(this, MovieTicketActivity::class.java)
            intent.putExtra(MovieTicketActivity.KEY_MOVIE_TICKET, movieTicketUi)
            startActivity(intent)
        }

        val seatTableLayout = findViewById<TableLayout>(R.id.seat_table)
        val priceTextView = findViewById<TextView>(R.id.seat_ticket_price)
        val seats = seatTableLayout.children.filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }.toList()

        seats.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, textView ->
                textView.setOnClickListener {
                    val discountPolicy = NormalDiscountPolicy(movieDetail.date, movieDetail.time)
                    val seat = Seat(SeatRow.of(rowIndex), SeatColumn.of(columnIndex))

                    when {
                        !selectedSeats.contains(seat) && movieDetail.isUpOfCount(selectedSeats.size) -> {
                            selectedSeats.add(seat)
                            totalPrice += discountPolicy.getDiscountPrice(seat.getSeatPrice())
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_background))
                        }
                        !selectedSeats.contains(seat) && !movieDetail.isUpOfCount(selectedSeats.size) -> Unit
                        else -> {
                            selectedSeats.remove(seat)
                            totalPrice -= discountPolicy.getDiscountPrice(seat.getSeatPrice())
                            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                        }
                    }
                    priceTextView.text = getString(R.string.total_price).format(totalPrice)
                }
            }
        }
    }

    companion object {
        const val KEY_MOVIE_DETAIL = "movieDetailUi"
    }
}
