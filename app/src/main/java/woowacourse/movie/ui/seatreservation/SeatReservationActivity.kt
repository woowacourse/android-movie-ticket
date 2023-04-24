package woowacourse.movie.ui.seatreservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.ABLE
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.DISABLE
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.MAX
import woowacourse.movie.ui.seatreservation.uimodel.BoxOffice.SelectState.REABLE
import woowacourse.movie.util.shortToast

class SeatReservationActivity : AppCompatActivity() {
    private val totalPrice: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_price) }
    private val movieName: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_title) }
    private val checkButton: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_check_btn) }
    private val boxOffice: BoxOffice by lazy { BoxOffice.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        initView()
        setClickEventOnSeat()
        setClickEventOnCheck()
    }

    private fun initView() {
        movieName.text = intent.getStringExtra(MOVIE_TITLE)
        totalPrice.text = getString(R.string.tv_seat_reservation_price).format(ZERO)
    }

    private fun setClickEventOnCheck() {
        val seatReservationDialog = SeatReservationDialog()

        checkButton.setOnClickListener {
            seatReservationDialog.init(this@SeatReservationActivity)
        }
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
        val ticketCount = intent.getIntExtra(TICKET_COUNT, ZERO)

        when (boxOffice.select(ticketCount, seat)) {
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
        findViewById<TableLayout>(R.id.tl_seat_reservation_seat).children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()

    companion object {
        private const val MOVIE_TITLE = "Title"
        private const val TICKET_COUNT = "Count"
        private const val ZERO = 0

        fun getIntent(context: Context, title: String, ticketCount: Int): Intent =
            Intent(context, SeatReservationActivity::class.java).apply {
                putExtra(MOVIE_TITLE, title)
                putExtra(TICKET_COUNT, ticketCount)
            }
    }
}
