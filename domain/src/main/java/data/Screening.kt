package data

import movie.TicketCount
import movie.pricePolicy.NormalPricePolicy
import movie.pricePolicy.PricePolicy
import movie.pricePolicy.PricePolicyInfo
import java.time.LocalDateTime

class Screening(
    val movie: Movie,
    val reservation: Reservation,
) {
    val title: String = movie.title

    fun reserve(peopleCount: TicketCount, reserveTime: LocalDateTime): Ticket = Ticket(
        title = movie.title,
        reserveTime = reserveTime,
        price = getMoviePrice(NormalPricePolicy(), reserveTime),
        peopleNumber = peopleCount.toInt(),
    )

    private fun getMoviePrice(pricePolicy: PricePolicy, reserveTime: LocalDateTime): Int =
        pricePolicy(PricePolicyInfo(DEFAULT_MOVIE_PRICE, reserveTime)).price

    companion object {
        private const val DEFAULT_MOVIE_PRICE = 13000
    }
}
