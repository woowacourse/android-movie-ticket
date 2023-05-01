package movie.domain.seat

enum class SeatRank(val row: Int, val price: Int) {
    B(0, 10_000),
    S(1, 15_000),
    A(2, 12_000);

    companion object {
        fun valueOf(index: Int): SeatRank {
            return values().find {
                it.row == index
            } ?: throw IllegalArgumentException("해당하는 값이 없습니다.")
        }
    }
}
