package woowacourse.movie.model

@JvmInline
value class SeatColumn(
    val value: Int,
) {
    init {
        require(value in MIN_COLUMN..MAX_COLUMN) { "좌석 열은 $MIN_COLUMN 에서 $MAX_COLUMN 사이의 열만 있습니다." }
    }

    companion object {
        private const val MIN_COLUMN = 1
        private const val MAX_COLUMN = 4
    }
}
