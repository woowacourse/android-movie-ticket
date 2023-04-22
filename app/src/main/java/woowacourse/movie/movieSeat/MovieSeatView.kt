package woowacourse.movie.movieSeat

import android.util.TypedValue
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import movie.seat.Seat
import movie.seat.SeatColumn
import movie.seat.SeatRow
import woowacourse.movie.R
import woowacourse.movie.utils.SeatUtil

class MovieSeatView(
    private val seatTableLayout: TableLayout,
    private val selectSeat: (seat: Seat, position: TextView) -> Unit,
) {

    private val seatTableConfiguration by lazy {
        seatTableLayout.children
            .filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>().toList() }.toList()
    }

    init {
        configureSeatTable()
        bind()
        initListener()
    }

    private fun bind() {
        seatTableConfiguration
    }

    private fun configureSeatTable(rowCount: Int = DEFAULT_ROW_SIZE, columnCount: Int = DEFAULT_COLUMN_SIZE) {
        repeat(rowCount) { row ->
            val tableRow = TableRow(seatTableLayout.context).apply {
                layoutParams = TableLayout.LayoutParams(0, 0, TABLE_COL_WEIGHT)
            }
            repeat(columnCount) { col ->
                tableRow.addView(makeSeatView(row, col))
            }
            seatTableLayout.addView(tableRow)
        }
    }

    private fun makeSeatView(row: Int, col: Int): TextView {
        val seat = Seat(SeatRow.of(row), SeatColumn.of(col))

        return TextView(seatTableLayout.context).apply {
            text = SeatUtil.getSeatPosition(seat)
            setBackgroundResource(R.drawable.select_background)
            gravity = Gravity.CENTER
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, TABLE_COL_WEIGHT)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, SEAT_TEXT_SIZE)
            setTextColor(seat.getColor())
        }
    }

    fun Seat.getColor(): Int {
        return when (row) {
            SeatRow.A, SeatRow.B -> seatTableLayout.context.getColor(R.color.b_class_seat)
            SeatRow.C, SeatRow.D -> seatTableLayout.context.getColor(R.color.s_class_seat)
            SeatRow.E -> seatTableLayout.context.getColor(R.color.a_class_seat)
        }
    }

    private fun initListener() {
        seatTableConfiguration.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, textView ->
                textView.setOnClickListener {
                    val seatRow = SeatRow.of(rowIndex)
                    val seatColumn = SeatColumn.of(columnIndex)
                    selectSeat(Seat(seatRow, seatColumn), textView)
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_ROW_SIZE = 5
        private const val DEFAULT_COLUMN_SIZE = 4
        private const val TABLE_COL_WEIGHT = 1f
        private const val SEAT_TEXT_SIZE = 22f
    }
}
