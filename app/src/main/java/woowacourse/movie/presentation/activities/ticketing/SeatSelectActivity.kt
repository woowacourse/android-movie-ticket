package woowacourse.movie.presentation.activities.ticketing

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.databinding.ActivitySeatSelectBinding
import woowacourse.movie.presentation.extensions.showBackButton
import woowacourse.movie.presentation.model.Seat
import woowacourse.movie.presentation.model.SeatColumn
import woowacourse.movie.presentation.model.SeatRow

class SeatSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeatSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatSelectBinding.inflate(layoutInflater).also { setContentView(it.root) }
        showBackButton()
        addTableLayoutMovieItems(rowSize = 5, colSize = 4)
    }

    private fun addTableLayoutMovieItems(rowSize: Int, colSize: Int) {
        SeatRow.make(rowSize).forEach { seatRow ->
            binding.seatTable.addView(makeSeatRow(seatRow, colSize))
        }
    }

    private fun makeSeatRow(row: SeatRow, colSize: Int): TableRow = TableRow(this).apply {
        SeatColumn.make(colSize).forEach { col ->
            addView(makeSeatColumn(row, col))
        }
    }

    private fun makeSeatColumn(row: SeatRow, col: SeatColumn): View =
        Seat(row, col).makeView(layoutInflater) {
            seatNumberTv.isChecked = !seatNumberTv.isChecked
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
