package com.example.domain.model.seat

data class SeatPosition(
    val row: SeatRow,
    val column: SeatColumn
) {
    constructor(y: Int, x: Int) : this(SeatRow.valueOf(y), SeatColumn.valueOf(x))
}
