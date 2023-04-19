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

        val seats = findViewById<TableLayout>(R.id.seat_selection)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()

        seats.forEachIndexed { index, view ->
            view.setOnClickListener {
                view.setBackgroundColor(getColor(R.color.seat_select_state))
            }
        }
    }
}
