package woowacourse.movie.domain

import woowacourse.movie.domain.seat.Seat

class MovieTicket(
    val title: String,
    val time: TicketTime,
    val peopleCount: PeopleCount,
    private val _seats: MutableSet<Seat> = mutableSetOf(),
    private val price: Price = Price(),
) {
    val seats: List<Seat>
        get() = _seats.map { Seat(it.row, it.column, it.rank) }

    fun reserveSeat(seat: Seat) {
        _seats.add(seat)
        increasePrice(seat.rank.price)
    }

    private fun increasePrice(amount: Int) {
        price.plus(amount)
    }

    fun cancelSeat(seat: Seat) {
        _seats.remove(seat)
        decreasePrice(seat.rank.price)
    }

    private fun decreasePrice(amount: Int) {
        price.minus(amount)
    }

    fun getDiscountPrice(): Price {
        var discountPrice = price.amount
        if (discountPrice == 0) return Price(discountPrice)

        if (time.isMovieDay()) discountPrice = (discountPrice * TICKET_MOVIE_DAY_SALE_RATE).toInt()
        if (time.isSaleTime()) discountPrice -= TICKET_TIME_SALE_AMOUNT
        return Price(discountPrice)
    }

    companion object {
        private const val TICKET_MOVIE_DAY_SALE_RATE = 0.9
        private const val TICKET_TIME_SALE_AMOUNT = 2000
    }
}
