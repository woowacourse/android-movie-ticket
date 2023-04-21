package woowacourse.movie.ui.seat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SelectedSeats
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.SeatModel

class SeatSelectionActivity : AppCompatActivity() {

    private var selectedSeats = SelectedSeats()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_selection)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initSeatTable()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSeatTable() {
        val seatTable = findViewById<TableLayout>(R.id.seat_table_layout)
        for (row in 1..ROW_SIZE) {
            val tableRow = TableRow(this)
            for (column in 1..COLUMN_SIZE) {
                tableRow.addView(getSeatView(row, column))
            }
            seatTable.addView(tableRow)
        }
    }

    private fun getSeatView(row: Int, column: Int): View {
        val seatView = LayoutInflater.from(this).inflate(R.layout.item_seat, null, false)
        val seat = SeatModel(row, column)
        seatView.apply {
            findViewById<TextView>(R.id.seat_view).text = seat.toString()
            setOnClickListener {
                clickSeat(seat.toDomain(), this)
            }
        }
        return seatView
    }

    private fun clickSeat(seat: Seat, seatView: View) {
        seatView.isSelected = !seatView.isSelected

        if (seatView.isSelected) {
            seatView.setBackgroundColor(getColor(R.color.seat_unselected_background))
            selectedSeats = selectedSeats.delete(seat)
        } else {
            seatView.setBackgroundColor(getColor(R.color.seat_selected_background))
            selectedSeats = selectedSeats.add(seat)
        }
    }

    companion object {
        const val ROW_SIZE = 5
        const val COLUMN_SIZE = 4
    }
}
