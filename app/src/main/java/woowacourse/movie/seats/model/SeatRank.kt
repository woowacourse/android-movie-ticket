package woowacourse.movie.seats.model

enum class SeatRank(val price: Int, val rowRange: IntRange) {
    B(10_000, (0..1)),
    A(12_000, (4..4)),
    S(15_000, (2..3)),
    ;

    companion object {
        fun of(rowIndex: Int): SeatRank = entries.first { rowIndex in it.rowRange }
    }
}
