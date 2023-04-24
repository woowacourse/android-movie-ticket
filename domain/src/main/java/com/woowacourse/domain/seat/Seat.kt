package com.woowacourse.domain.seat

data class Seat(val row: SeatRow, val column: SeatColumn) : Comparable<Seat> {

    fun getSeatTier() = row.getSeatTier()

    override fun compareTo(other: Seat): Int {
        if (this.row.value == other.row.value) {
            return if (this.column.value < other.column.value) -1 else 1
        }
        if (this.row.value < other.row.value) {
            return -1
        }
        return 1
    }
}
