package woowacourse.movie.seatselection

import android.util.TypedValue
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import domain.reservation.SeatSelection
import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow
import domain.seat.SeatState
import woowacourse.movie.R
import woowacourse.movie.model.ScreeningSeatInfo
import woowacourse.movie.util.toColor
import woowacourse.movie.util.toScreeningSeat

class ScreeningSeatView(
    private val seatTable: TableLayout,
    private val seatSelection: SeatSelection
) {

    private val seatTableConfiguration by lazy {
        seatTable.findViewById<TableLayout>(R.id.seat_table_layout)
            .children
            .filterIsInstance<TableRow>()
            .flatMap { it.children }
            .filterIsInstance<TextView>()
            .toList()
    }

    init {
        configureSeatTable()
        bind()
    }

    private fun bind() {
        seatTableConfiguration.forEachIndexed { seatPosition, seatView ->
            val seat = seatPosition.toScreeningSeat()

            when (seatSelection[seat]) {
                SeatState.SELECTED -> seatView.isSelected = true
                else -> seatView.isSelected = false
            }
        }
    }

    private fun configureSeatTable(rowCount: Int = DEFAULT_ROW_SIZE, colCount: Int = DEFAULT_COL_SIZE) {
        repeat(rowCount) { row ->
            val tableRow = TableRow(seatTable.context).apply {
                layoutParams = TableLayout.LayoutParams(0, 0, TABLE_COL_WEIGHT)
            }
            repeat(colCount) { col ->
                tableRow.addView(makeSeat(row, col))
            }
            seatTable.addView(tableRow)
        }
    }

    private fun makeSeat(row: Int, col: Int): TextView {
        val seat = ScreeningSeat.valueOf(
            SeatRow.valueOf(row),
            SeatColumn.valueOf(col)
        )

        return TextView(seatTable.context).apply {
            text = ScreeningSeatInfo(SeatRow.valueOf(row), SeatColumn.valueOf(col)).toString()
            setBackgroundResource(R.drawable.seat_background)
            gravity = Gravity.CENTER
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, TABLE_COL_WEIGHT)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, SEAT_TEXT_SIZE)
            setTextColor(seat.rate.toColor())
        }
    }

    fun setSeatViewClickedListener(
        updateSeatNavigation: (seatSelection: SeatSelection) -> Unit
    ) {
        seatTableConfiguration.forEachIndexed { seatPosition, seatView ->
            seatView.setOnClickListener {
                val seat = seatPosition.toScreeningSeat()

                seatSelection[seat].apply {
                    runCatching {
                        onSeatViewClicked(seatView, seat)
                    }.onFailure {
                        Toast.makeText(seatTable.context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                updateSeatNavigation(seatSelection)
            }
        }
    }

    private fun onSeatViewClicked(seatView: TextView, seat: ScreeningSeat) {
        if (seatSelection[seat] == SeatState.SELECTED) {
            seatSelection.cancelSeat(seat)
            seatView.isSelected = false
        } else {
            seatSelection.selectSeat(seat)
            seatView.isSelected = true
        }
    }

    companion object {
        private const val DEFAULT_ROW_SIZE = 5
        private const val DEFAULT_COL_SIZE = 4
        private const val TABLE_COL_WEIGHT = 1f
        private const val SEAT_TEXT_SIZE = 22f
    }
}
