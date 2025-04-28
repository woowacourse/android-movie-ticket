package woowacourse.movie.domain.model.seat

enum class SeatRank(val price: Int) {
    BRANK(10_000),
    ARANK(12_000),
    SRANK(15_000),
    ;

    companion object {
        private const val ERROR_NOT_REGISTER_RANK = "아직 등급이 등록되지 않았습니다."

        fun withRow(row: Row): SeatRank {
            return when (row.value) {
                'A', 'B' -> BRANK
                'C', 'D' -> SRANK
                'E' -> ARANK
                else -> throw IllegalArgumentException(ERROR_NOT_REGISTER_RANK)
            }
        }
    }
}
