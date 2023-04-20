package movie.seat

enum class SeatColumn {
    ONE,
    TWO,
    THREE,
    FOUR,
    ;

    companion object {
        fun of(value: Int): SeatColumn {
            return when (value) {
                0 -> ONE
                1 -> TWO
                2 -> THREE
                3 -> FOUR
                else -> throw IllegalArgumentException()
            }
        }
    }
}
