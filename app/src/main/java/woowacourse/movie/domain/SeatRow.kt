package woowacourse.movie.domain

enum class SeatRow {
    A, B, C, D, E;

    companion object {
        fun of(row: Int) = values().getOrNull(row)
    }
}
