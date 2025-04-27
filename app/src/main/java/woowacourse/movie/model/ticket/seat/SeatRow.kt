package woowacourse.movie.model.ticket.seat

@JvmInline
value class SeatRow(
    val value: Int,
) {
    init {
        val alphabetCount = MAX_ROW_SEAT_LENGTH
        require(value in 0 until alphabetCount) {
            "좌석의 행 값은 0부터 ${alphabetCount - 1} 사이를 사용하세요"
        }
    }

    val rowSeatText: String
        get() = ('A'.code + value).toChar().toString()

    companion object {
        private const val MAX_ROW_SEAT_LENGTH = 26
    }
}
