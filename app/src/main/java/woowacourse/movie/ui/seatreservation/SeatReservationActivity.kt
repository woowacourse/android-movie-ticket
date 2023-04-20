package woowacourse.movie.ui.seatreservation

import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R

class SeatReservationActivity : AppCompatActivity() {
    private val seatingChart: Sequence<TextView> by lazy { createSeatingChart() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_reservation)

        setClickEventOnSeat()
        setClickEventOnCheck()
    }

    private fun setClickEventOnCheck() {
        val button = findViewById<TextView>(R.id.tv_seat_reservation_check_btn)
        button.setOnClickListener { view ->
            view.isSelected = true

            // dialog
        }
    }

    private fun setClickEventOnSeat() {
        seatingChart.forEachIndexed { location, view ->
            view.setOnClickListener { view ->
                Log.d("123123", location.toString())

                view.isSelected = !view.isSelected
            }
        }
    }

    private fun createSeatingChart() =
        findViewById<TableLayout>(R.id.tl_seat_reservation_seat).children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
}
