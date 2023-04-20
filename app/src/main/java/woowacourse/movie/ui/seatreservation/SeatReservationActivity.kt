package woowacourse.movie.ui.seatreservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.ui.seatreservation.domain.Money
import woowacourse.movie.ui.seatreservation.domain.Seat
import woowacourse.movie.ui.seatreservation.domain.Total

class SeatReservationActivity : AppCompatActivity() {
    private val seatingChart: Sequence<TextView> by lazy { createSeatingChart() }
    private val sum: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_price) }
    private val movieName: TextView by lazy { findViewById<TextView>(R.id.tv_seat_reservation_title) }
    private val total: Total by lazy { Total.from() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        initView()
        setClickEventOnSeat()
        setClickEventOnCheck()
    }

    private fun initView() {
        movieName.text = intent.getStringExtra(MOVIE_TITLE)
        sum.text = getString(R.string.tv_seat_reservation_price).format(ZERO)
    }

    private fun setClickEventOnCheck() {
        val button = findViewById<TextView>(R.id.tv_seat_reservation_check_btn)
        button.setOnClickListener { view ->
            view.isSelected = true

            // dialog
        }
    }

    private fun setClickEventOnSeat() {
        seatingChart.forEachIndexed { seatLocation, view ->
            view.setOnClickListener { view ->
                view.isSelected = !view.isSelected

                val seat = Seat.valueOf(seatLocation)
                val price = Money.from(seat.price)
                total.calculate(price)

                sum.text = getString(R.string.tv_seat_reservation_price).format(total.price)
            }
        }
    }

    private fun createSeatingChart() =
        findViewById<TableLayout>(R.id.tl_seat_reservation_seat).children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()

    companion object {
        private const val MOVIE_TITLE = "Title"
        private const val ZERO = 0

        fun getIntent(context: Context, title: String): Intent {
            return Intent(context, SeatReservationActivity::class.java).apply {
                putExtra(MOVIE_TITLE, title)
            }
        }
    }
}
