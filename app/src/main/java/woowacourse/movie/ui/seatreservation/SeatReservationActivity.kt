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
import woowacourse.movie.ui.seatreservation.domain.BoxOffice

class SeatReservationActivity : AppCompatActivity() {
    private val seatingChart: Sequence<TextView> by lazy { createSeatingChart() }
    private val totalPrice: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_price) }
    private val movieName: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_title) }
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
        val button = findViewById<TextView>(R.id.tv_seat_reservation_check_btn)
        button.setOnClickListener { view ->
            view.isSelected = true

            // dialog
        }
    }

    private fun setClickEventOnSeat() {
        var count = 0
        seatingChart.forEachIndexed { seatLocation, view ->
            view.setOnClickListener { view ->
                updateTotalView(view, seatLocation)

                count++
            }
        }
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
