package entity

import movie.MovieTicket
import movie.TicketCount
import movie.pricePolicy.PricePolicyInfo
import java.io.Serializable
import java.time.LocalDateTime

class Screening(
    val movie: Movie,
    val reservation: Reservation,
) : MovieListDto, Serializable {
    fun reserve(peopleCount: TicketCount, reserveTime: LocalDateTime): MovieTicket = MovieTicket(
        eachPrice = movie.pricePolicy(PricePolicyInfo(DEFAULT_MOVIE_PRICE, reserveTime)).price,
        count = peopleCount,
        title = movie.title,
        date = reserveTime.toLocalDate(),
        time = reserveTime.toLocalTime(),
    )

    companion object {
        private const val DEFAULT_MOVIE_PRICE = 13000
    }
}
