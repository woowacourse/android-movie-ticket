package woowacourse.movie.domain.model

import woowacourse.movie.domain.model.MovieTime.Companion.getMovieTimes
import java.time.LocalDate

data class BookingInfo(
    val movie: Movie,
) {
    private var _date: LocalDate = movie.startDate
    val date: LocalDate get() = _date

    private var _movieTime: MovieTime = getMovieTimes(DateType.from(date)).first()
    val movieTime: MovieTime get() = _movieTime

    private var _ticketCount: Int = 0
    val ticketCount: Int get() = _ticketCount

    val eachPrice: Int = DEFAULT_TICKET_PRICE
    val totalPrice: Int get() = ticketCount * eachPrice

    fun updateDate(date: LocalDate) {
        _date = date
        _movieTime = getMovieTimes(DateType.from(date)).first()
    }

    fun updateMovieTime(movieTime: MovieTime) {
        _movieTime = movieTime
    }

    fun increaseTicketCount(count: Int = 1) {
        _ticketCount += count
    }

    fun decreaseTicketCount(count: Int = 1) {
        if (ticketCount - count < 0) {
            _ticketCount = 0
        } else {
            _ticketCount -= count
        }
    }

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
