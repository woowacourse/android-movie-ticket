package woowacourse.movie.ui.seatreservation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.MovieData
import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.Ticket
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.ABLE
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.DISABLE
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.MAX
import woowacourse.movie.ui.seatreservation.uimodel.SelectState.REABLE
import woowacourse.movie.util.shortToast

class SeatReservationActivity : AppCompatActivity() {
    private val totalPrice: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_price) }
    private val movieName: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_title) }
    private val checkButton: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_check_btn) }
    private val boxOffice: BoxOffice by lazy { BoxOffice.create(getTicket().bookedDateTime) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        initView()
        setClickEventOnSeat()
        setClickEventOnCheck()
    }

    private fun initView() {
        val movieId = intent.getLongExtra(TICKET_MOVIE_NAME, ZERO.toLong())

        movieName.text = MovieData.findMovieById(movieId).title
        totalPrice.text = getString(R.string.tv_seat_reservation_price).format(ZERO)
    }

    private fun setClickEventOnCheck() {
        val seatReservationDialog = SeatReservationDialog()

        checkButton.setOnClickListener {
            seatReservationDialog.init(this@SeatReservationActivity, setReservationInfo())
        }
    }

    private fun setReservationInfo(): ReservationInfo {
        val price = findViewById<TextView>(R.id.tv_seat_reservation_price).text

        return ReservationInfo(getTicket(), price.toString(), boxOffice.seats)
    }

    private fun getTicket() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getParcelableExtra(TICKET, Ticket::class.java)
            ?: throw IllegalArgumentException()
    } else {
        intent.getParcelableExtra(TICKET) ?: throw IllegalArgumentException()
    }

    private fun setClickEventOnSeat() {
        val seatingChart: Sequence<TextView> = createSeatingChart()

        seatingChart.forEachIndexed { seatLocation, view ->
            view.setOnClickListener { seat ->
                updateViewSelected(seat, seatLocation)
            }
        }
    }

    private fun updateViewSelected(seat: View, seatLocation: Int) {
        val count = intent.getIntExtra(TICKET_COUNT, ZERO)

        when (boxOffice.select(count, seat)) {
            ABLE -> updateAbleState(seat, seatLocation)
            REABLE -> updateReableState(seat, seatLocation)
            DISABLE -> shortToast(R.string.st_seat_reservation_over)
            MAX -> updateMaxState(seat, seatLocation)
        }
    }

    private fun updateAbleState(seat: View, seatLocation: Int) {
        updateTotalView(seat, seatLocation)
        seat.isSelected = true
    }

    private fun updateReableState(seat: View, seatLocation: Int) {
        updateTotalView(seat, seatLocation)
        seat.isSelected = false
        if (checkButton.isSelected) {
            checkButton.isEnabled = false
            checkButton.isSelected = false
        }
    }

    private fun updateMaxState(seat: View, seatLocation: Int) {
        updateTotalView(seat, seatLocation)
        seat.isSelected = true
        checkButton.isEnabled = true
        checkButton.isSelected = true
    }

    private fun updateTotalView(view: View, seatLocation: Int) {
        val sum = boxOffice.calculate(view, seatLocation)

        totalPrice.text = getString(R.string.tv_seat_reservation_price).format(sum)
    }

    private fun createSeatingChart() =
        findViewById<TableLayout>(R.id.tl_seat_reservation_seat).children.filterIsInstance<TableRow>()
            .flatMap { it.children }.filterIsInstance<TextView>()

    companion object {
        private const val TICKET_MOVIE_NAME = "Movie"
        private const val TICKET = "Ticket"
        private const val TICKET_COUNT = "Count"
        private const val ZERO = 0

        fun getIntent(context: Context, ticket: Ticket): Intent =
            Intent(context, SeatReservationActivity::class.java).apply {
                putExtra(TICKET, ticket)
                putExtra(TICKET_COUNT, ticket.count)
                putExtra(TICKET_MOVIE_NAME, ticket.movieId)
            }
    }
}
