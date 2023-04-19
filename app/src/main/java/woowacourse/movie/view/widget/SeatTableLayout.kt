package woowacourse.movie.view.widget

import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.TableSize
import woowacourse.movie.domain.seat.MovieSeatRow
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.view.data.SeatTable
import woowacourse.movie.view.mapper.SeatMapper.toView

class SeatTableLayout(
    private val tableLayout: TableLayout,
) {
    fun selectedSeats(): List<SeatView> {
        return tableLayout.children.flatMap { tableRow ->
            ((tableRow as TableRow).children as Sequence<SeatView>).filter { seatView ->
                seatView.isSeatSelected
            }
        }.toList()
    }

    fun makeSeatTable(seats: SeatTable, maxSelectableSeat: Int) {
        val tableLayout = tableLayout.findViewById<TableLayout>(R.id.seat_selection_table)
        tableLayout.weightSum = seats.size.row.toFloat()
        (0 until seats.size.row).forEach {
            tableLayout.addView(makeSeatRow(it, seats, maxSelectableSeat))
        }
    }

    private fun makeSeatRow(row: Int, seats: SeatTable, maxSelectableSeat: Int): TableRow {
        val tableRow = TableRow(tableLayout.context)
        tableRow.weightSum = seats.size.column.toFloat()
        tableRow.layoutParams = TableLayout.LayoutParams(0, 0, 1f)
        (0 until seats.size.column).forEach {
            tableRow.addView(makeSeatCell(row, it, seats, maxSelectableSeat))
        }
        return tableRow
    }

    private fun makeSeatCell(
        row: Int,
        column: Int,
        seats: SeatTable,
        maxSelectableSeat: Int
    ): SeatView {
        return SeatView.from(
            tableLayout.context, seats.getSeat(row, column).toView()
        ) {
            selectedSeats().size < maxSelectableSeat
        }
    }

    companion object {
        fun from(
            tableLayout: TableLayout,
            row: Int,
            column: Int,
            maxSelectableSeat: Int
        ): SeatTableLayout {
            return makeSeatTableLayout(
                tableLayout, makeSeatTable(row, column), maxSelectableSeat
            )
        }

        private fun makeSeatTable(row: Int, column: Int): SeatTable {
            return (0..row * column).map {
                val y = it / column
                val x = it % column
                Seat(MovieSeatRow(y), x)
            }.let {
                SeatTable(Seats(it), TableSize(row, column))
            }
        }

        private fun makeSeatTableLayout(
            tableLayout: TableLayout,
            seatTable: SeatTable,
            maxSelectableSeat: Int
        ): SeatTableLayout {
            return SeatTableLayout(tableLayout).also {
                it.makeSeatTable(seatTable, maxSelectableSeat)
            }
        }
    }
}
