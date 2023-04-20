package domain.seat

enum class SeatRow {
    A,
    B,
    C,
    D,
    E;

    companion object {

        private const val SEAT_ROW_ERROR = "[ERROR] 해당 위치의 열은 존재하지 않습니다."

        fun valueOf(position: Int): SeatRow {
            return values().find { it.ordinal == position }
                ?: throw IllegalArgumentException(SEAT_ROW_ERROR)
        }
    }
}
