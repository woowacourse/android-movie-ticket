package woowacourse.movie.domain.seat

enum class SeatRow {
    A, B, C, D, E;

    companion object {
        fun of(row: Int) = values().getOrNull(row) ?: throw IllegalStateException()
    }
}
