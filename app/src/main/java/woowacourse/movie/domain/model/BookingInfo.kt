package woowacourse.movie.domain.model

import woowacourse.movie.domain.model.MovieTime.Companion.getMovieTimes

data class BookingInfo(
    val movie: Movie,
) {
    private var _date: MovieDate = movie.startDate
    val date: MovieDate get() = _date

    private var _movieTime: MovieTime = getMovieTimes(DateType.from(date)).first()
    val movieTime: MovieTime get() = _movieTime

    val ticketCount: TicketCount = TicketCount()

    val eachPrice: Int = DEFAULT_TICKET_PRICE
    val totalPrice: Int get() = ticketCount * eachPrice

    fun updateDate(date: MovieDate) {
        _date = date
        _movieTime = getMovieTimes(DateType.from(date)).first()
    }

    fun updateMovieTime(movieTime: MovieTime) {
        _movieTime = movieTime
    }

    fun increaseTicketCount(count: Int = 1) {
        ticketCount.increase(count)
    }

    fun decreaseTicketCount(count: Int = 1) {
        ticketCount.decrease(count)
    }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
