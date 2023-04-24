package woowacourse.movie.view.widget

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.domain.seat.MovieSeatRow
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.view.data.SeatTable
import woowacourse.movie.view.data.SeatsViewData
import woowacourse.movie.view.data.TableSize
import woowacourse.movie.view.getSerializable
import woowacourse.movie.view.mapper.MovieSeatMapper.toView

class SeatTableLayout(
    private val tableLayout: TableLayout,
    override val saveStateKey: String
) : SaveState {
    var onSelectSeat: (SeatsViewData) -> Unit = {}
    var seatSelectCondition: (Int) -> Boolean = { true }

    fun selectedSeats(): SeatsViewData {
        return tableLayout.children.flatMap { tableRow ->
            (tableRow as TableRow).children.filterIsInstance<SeatView>().filter {
                it.isSeatSelected
            }
        }.map {
            it.data
        }.let {
            SeatsViewData(it.toList())
        }
    }

    fun makeSeatTable(seats: SeatTable) {
        val tableLayout = tableLayout.findViewById<TableLayout>(R.id.seat_selection_table)
        tableLayout.weightSum = seats.size.row.toFloat()
        (0 until seats.size.row).forEach {
            tableLayout.addView(makeSeatRow(it, seats))
        }
    }

    private fun makeSeatRow(row: Int, seats: SeatTable): TableRow {
        val tableRow = TableRow(tableLayout.context)
        tableRow.weightSum = seats.size.column.toFloat()
        tableRow.layoutParams = TableLayout.LayoutParams(0, 0, 1f)
        (0 until seats.size.column).forEach {
            tableRow.addView(makeSeatCell(row, it, seats))
        }
        return tableRow
    }

    private fun makeSeatCell(
        row: Int,
        column: Int,
        seats: SeatTable
    ): SeatView {
        return SeatView.from(
            tableLayout.context,
            seats.getSeat(row, column).toView(),
            { seatSelectCondition(selectedSeats().seats.size) }
        ) {
            onSelectSeat(selectedSeats())
        }
    }

    override fun save(outState: Bundle) {
        outState.putSerializable(saveStateKey, selectedSeats())
    }

    override fun load(savedInstanceState: Bundle?) {
        savedInstanceState ?: return
        val seats = savedInstanceState.getSerializable<SeatsViewData>(saveStateKey) ?: return
        seats.seats.forEach {
            findSeatViewByRowAndColumn(it.row, it.column)?.callOnClick()
        }
    }

    private fun findSeatViewByRowAndColumn(row: Int, column: Int): SeatView? {
        return tableLayout.children.flatMap { tableRow ->
            (tableRow as TableRow).children
        }.filterIsInstance<SeatView>().find {
            it.data.row == row && it.data.column == column
        }
    }

    companion object {
        fun from(
            tableLayout: TableLayout,
            row: Int,
            column: Int,
            saveStateKey: String
        ): SeatTableLayout {
            val seatTable = makeSeatTable(row, column)
            return SeatTableLayout(tableLayout, saveStateKey).also {
                it.makeSeatTable(seatTable)
            }
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
    }
}
