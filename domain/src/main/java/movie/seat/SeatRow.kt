package movie.seat

enum class SeatRow {
    A,
    B,
    C,
    D,
    E,
    ;

    companion object {
        fun of(value: Int): SeatRow {
            return when (value) {
                0 -> A
                1 -> B
                2 -> C
                3 -> D
                4 -> E
                else -> throw IllegalArgumentException()
            }
        }
    }
}
