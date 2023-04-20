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
import woowacourse.movie.view.data.SeatsViewData
import woowacourse.movie.view.mapper.MovieSeatMapper.toView

class SeatTableLayout(
    private val tableLayout: TableLayout,
    private val onSelectSeat: (SeatsViewData) -> Unit
) {
    fun selectedSeats(): SeatsViewData {
        return tableLayout.children.flatMap { tableRow ->
            ((tableRow as TableRow).children as Sequence<SeatView>).filter { seatView ->
                seatView.isSeatSelected
            }
        }.map {
            it.data
        }.let {
            SeatsViewData(it.toList())
        }
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
        return SeatView.from(tableLayout.context, seats.getSeat(row, column).toView(), {
            selectedSeats().seats.size < maxSelectableSeat
        }) {
            onSelectSeat(selectedSeats())
        }
    }

    companion object {
        fun from(
            tableLayout: TableLayout,
            row: Int,
            column: Int,
            maxSelectableSeat: Int,
            onSelectSeat: (SeatsViewData) -> Unit
        ): SeatTableLayout {
            return makeSeatTableLayout(
                tableLayout, makeSeatTable(row, column), maxSelectableSeat, onSelectSeat
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
            maxSelectableSeat: Int,
            onSelectSeat: (SeatsViewData) -> Unit
        ): SeatTableLayout {
            return SeatTableLayout(tableLayout, onSelectSeat).also {
                it.makeSeatTable(seatTable, maxSelectableSeat)
            }
        }
    }
}
