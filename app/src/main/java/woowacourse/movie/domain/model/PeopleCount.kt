package woowacourse.movie.domain.model

@JvmInline
value class PeopleCount(val count: Int) {
    fun ticketPrice(type: TicketType): Int {
        return type.price * count
    }
}
