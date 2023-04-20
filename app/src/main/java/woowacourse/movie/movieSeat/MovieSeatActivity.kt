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
        initMovieView()
        initListener()
    }

    private fun initMovieView() {
        val movieTitleView = findViewById<TextView>(R.id.seat_movie_title)
        movieTitleView.text = movieDetail.title

        val nextButton = findViewById<Button>(R.id.seat_next_button)
        nextButton.setOnClickListener { startActivity(makeIntent()) }
    }

    private fun initListener() {
        val seatTableLayout = findViewById<TableLayout>(R.id.seat_table)
        val seats = seatTableLayout.children.filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }.toList()

        seats.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, textView ->
                textView.setOnClickListener {
                    selectSeat(rowIndex, columnIndex, textView)
                }
            }
        }
    }

    private fun selectSeat(
        rowIndex: Int,
        columnIndex: Int,
        textView: TextView,
    ) {
        val priceTextView = findViewById<TextView>(R.id.seat_ticket_price)
        val seat = Seat(SeatRow.of(rowIndex), SeatColumn.of(columnIndex))

        when {
            !selectedSeats.contains(seat) && movieDetail.isUpOfCount(selectedSeats.size) -> { selected(seat, textView) }
            !selectedSeats.contains(seat) && !movieDetail.isUpOfCount(selectedSeats.size) -> Unit
            else -> { deselected(seat, textView) }
        }
        priceTextView.text = getString(R.string.total_price).format(totalPrice)
    }

    private fun deselected(
        seat: Seat,
        positionTextView: TextView,
    ) {
        val discountPolicy = NormalDiscountPolicy(movieDetail.date, movieDetail.time)
        selectedSeats.remove(seat)
        totalPrice -= discountPolicy.getDiscountPrice(seat.getSeatPrice())
        positionTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
        positionTextView.isSelected = false
    }

    private fun selected(
        seat: Seat,
        positionTextView: TextView,
    ) {
        val discountPolicy = NormalDiscountPolicy(movieDetail.date, movieDetail.time)
        selectedSeats.add(seat)
        totalPrice += discountPolicy.getDiscountPrice(seat.getSeatPrice())
        positionTextView.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_background))
        positionTextView.isSelected = true
    }

    private fun makeIntent(): Intent {
        val intent = Intent(this, MovieTicketActivity::class.java)
        val movieTicketUi = makeMovieTicketUi()
        intent.putExtra(MovieTicketActivity.KEY_MOVIE_TICKET, movieTicketUi)
        return intent
    }

    private fun makeMovieTicketUi(): MovieTicketUi {
        return MovieTicketUi(
            totalPrice = totalPrice,
            count = movieDetail.count,
            title = movieDetail.title,
            date = movieDetail.date,
            time = movieDetail.time,
            seats = selectedSeats.map { it.getSeatPosition() },
        )
    }

    companion object {
        const val KEY_MOVIE_DETAIL = "movieDetailUi"
    }
}
