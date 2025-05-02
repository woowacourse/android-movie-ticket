package woowacourse.movie.domain

@JvmInline
value class Column(val value: Char) {
    init {
        require(value in MIN_COLUMN .. MAX_COLUMN) { "허용되지 않은 좌석의 열입니다." }
    }

    fun price(): Int = rate().price

    private fun rate(): SeatRating {
        return when (value) {
            'C'-> SeatRating.S
            'D' -> SeatRating.S
            'E' -> SeatRating.A
            else -> SeatRating.B
        }
    }

    companion object {
        private const val MIN_COLUMN = 'A'
        private const val MAX_COLUMN = 'E'
    }
}