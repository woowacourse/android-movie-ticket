package woowacourse.movie.model

data class SeatRow(
    val value: Int,
) {
    init {
        require(value in MIN_ROW..MAX_ROW) { "좌석 행은 $MIN_ROW~${MAX_ROW}행까지만 있습니다." }
    }

    companion object {
        private const val MIN_ROW = 1
        private const val MAX_ROW = 5
    }
}
