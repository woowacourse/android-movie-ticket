package woowacourse.movie.model

import domain.seat.ScreeningSeat
import domain.seat.SeatColumn
import domain.seat.SeatRow
import java.io.Serializable

data class ScreeningSeatInfo(
    val row: SeatRow,
    val column: SeatColumn,
) : Serializable, Comparable<ScreeningSeatInfo> {

    override fun compareTo(other: ScreeningSeatInfo): Int {
        return if (row != other.row) {
            row.compareTo(other.row)
        } else {
            column.compareTo(other.column)
        }
    }

    override fun toString(): String {
        return SEAT_FORM.format(
            START_ROW + row.ordinal,
            column.ordinal
        )
    }

    companion object {
        private const val SEAT_FORM = "%c%s"
        const val START_ROW = 'A'
    }
}

fun ScreeningSeat.toUIModel() = ScreeningSeatInfo(row, column)
