package domain.seat

enum class SeatColumn {
    FIRST,
    SECOND,
    THIRD,
    FOURTH;

    companion object {
        private const val SEAT_COLUMN_ERROR = "[ERROR] 해당 위치의 열은 존재하지 않습니다."

        fun valueOf(position: Int): SeatColumn {
            return values().find { it.ordinal == position }
                ?: throw IllegalArgumentException(SEAT_COLUMN_ERROR)
        }
    }
}
