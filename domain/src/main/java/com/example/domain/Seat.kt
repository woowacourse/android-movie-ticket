package com.example.domain

data class Seat(val column: Int, val row: Int) {

    val price = type.price

    private val type
        get() : SeatType = when (column) {
            1, 2 -> SeatType.BType
            3, 4 -> SeatType.SType
            5 -> SeatType.AType
            else -> throw IllegalArgumentException(ERROR_INVALID_ROW)
        }

    init {
        require(row in MIN_ROW..MAX_ROW) {
            ERROR_INVALID_ROW
        }
        require(column in MIN_COLUMN..MAX_COLUMN) {
            ERROR_INVALID_COLUMN
        }
    }

    companion object {
        private const val MIN_ROW = 1
        private const val MAX_ROW = 4
        private const val MIN_COLUMN = 1
        private const val MAX_COLUMN = 5
        private const val ERROR_INVALID_ROW = "[ERROR] 올바른 좌석(행)이 아닙니다."
        private const val ERROR_INVALID_COLUMN = "[ERROR] 올바른 좌석(열)이 아닙니다."
    }
}
