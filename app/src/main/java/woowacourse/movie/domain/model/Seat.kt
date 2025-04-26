package woowacourse.movie.domain.model

data class Seat(
    val row: Int,
    val col: Int,
    val ticketType: TicketType,
) {
    fun price(): Int = ticketType.price
}
