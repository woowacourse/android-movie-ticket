package woowacourse.movie.seatSelection

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R

class SeatSelectionActivity : AppCompatActivity() {
    private val seatSelectionTable by lazy { findViewById<TableLayout>(R.id.seat_selection_table) }
    private val seatSelectionTableRows by lazy { seatSelectionTable.children.filterIsInstance<TableRow>() }
    private val seatSelectionTableBoxes by lazy { seatSelectionTableRows.map { it.children.filterIsInstance<TextView>() } }

    private val _seats = MutableList(5) { MutableList(4) { false } }
    private val seats get() = _seats

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seat_selection_activity)

        drawSeatSelection()
        initSeatSelection()
    }

    private fun initSeatSelection() {
        seatSelectionTableBoxes.forEachIndexed { row, it ->
            it.forEachIndexed { col, box ->
                "${'A'.plus(row)}${col + 1}".also { box.text = it }
                box.setOnClickListener {
                    setSeatSelection(row, col)
                    drawSeatSelection()
                }
            }
        }
    }

    private fun drawSeatSelection() {
        seatSelectionTableBoxes.forEachIndexed { row, it ->
            it.forEachIndexed { col, box ->
                when (seats[row][col]) {
                    true -> box.setBackgroundResource(R.color.seat_selection_seat_selected)
                    false -> box.setBackgroundResource(R.color.seat_selection_seat_unselected)
                }
            }
        }
    }

    private fun setSeatSelection(row: Int, col: Int) {
        _seats[row][col] = !_seats[row][col]
    }
}
