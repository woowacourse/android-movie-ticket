package entity

import movie.TicketCount
import movie.pricePolicy.NormalPricePolicy
import movie.pricePolicy.PricePolicy
import movie.pricePolicy.PricePolicyInfo
import java.io.Serializable
import java.time.LocalDateTime

class Screening(
    val movie: Movie,
    val reservation: Reservation,
) : MovieListDto, Serializable {
    fun reserve(peopleCount: TicketCount, reserveTime: LocalDateTime): MovieTicket {
        val movieTicketPeople = (0 until peopleCount.toInt())
            .map { MovieTicketPerson(getMoviePrice(NormalPricePolicy(), reserveTime)) }

        return MovieTicket(
            title = movie.title,
            reserveTime = reserveTime,
            people = movieTicketPeople,
        )
    }

    private fun getMoviePrice(pricePolicy: PricePolicy, reserveTime: LocalDateTime): Int =
        pricePolicy(PricePolicyInfo(DEFAULT_MOVIE_PRICE, reserveTime)).price

    companion object {
        private const val DEFAULT_MOVIE_PRICE = 13000
    }
}
