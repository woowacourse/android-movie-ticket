package woowacourse.movie.view

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R

class SeatSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)

        val seats = (0..24).associateWith { false }.toMutableMap()

        val seatsView = findViewById<TableLayout>(R.id.seat_selection)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()

        seatsView.forEachIndexed { index, view ->
            view.setOnClickListener {
                if (seats[index] == true) {
                    view.setBackgroundColor(getColor(R.color.white))
                    seats[index] = false
                } else {
                    view.setBackgroundColor(getColor(R.color.seat_select_state))
                    seats[index] = true
                }
            }
        }
    }
}
