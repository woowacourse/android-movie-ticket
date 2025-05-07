package woowacourse.movie.model.ticket.seat

@JvmInline
value class SeatRow(
    val index: Int,
) {
    init {
        val alphabetCount = MAX_ROW_SEAT_LENGTH
        require(index in 0 until alphabetCount) {
            "좌석의 행 값은 0부터 ${alphabetCount - 1} 사이를 사용하세요"
        }
    }

    companion object {
        private const val MAX_ROW_SEAT_LENGTH = 26
    }
}
