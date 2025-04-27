package woowacourse.movie.model.ticket.seat

@JvmInline
value class SeatCol(
    val value: Int,
) {
    val colSeatText: String
        get() = (value + 1).toString()
}
