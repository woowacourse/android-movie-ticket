package woowacourse.movie.model.ticket.seat

@JvmInline
value class SeatCol(
    val index: Int,
) {
    init {
        require(index >= 0) {
            "좌석의 열 값은 0 이상이어야 합니다."
        }
    }
}
