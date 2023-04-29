package com.example.domain.model.seat

enum class SeatRow {
    A,
    B,
    C,
    D,
    E;

    companion object {
        private const val ERROR_ROW_RANGE = "[ERROR] ROW의 범위는 1에서 5사이입니다."
        private val ROWS: Map<Int, SeatRow> = values().associateBy { it.ordinal }
        fun valueOf(y: Int): SeatRow =
            ROWS[y - 1] ?: throw IllegalArgumentException(ERROR_ROW_RANGE)
    }
}
