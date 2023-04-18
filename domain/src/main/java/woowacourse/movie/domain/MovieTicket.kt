package woowacourse.movie.domain

class MovieTicket(
    val title: String,
    val time: TicketTime,
    val peopleCount: PeopleCount,
) {
    fun getPrice(): Int {
        var price = TICKET_PRICE * peopleCount.count
        if (time.isMovieDay()) price = (price * TICKET_MOVIE_DAY_SALE_RATE).toInt()
        if (time.isSaleTime()) price -= TICKET_TIME_SALE_AMOUNT
        return price
    }

    companion object {
        private const val TICKET_PRICE = 13000
        private const val TICKET_MOVIE_DAY_SALE_RATE = 0.9
        private const val TICKET_TIME_SALE_AMOUNT = 2000
    }
}
