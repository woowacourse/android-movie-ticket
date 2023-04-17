package movie

import data.Screening
import data.Ticket
import movie.pricePolicy.NormalPricePolicy
import movie.pricePolicy.PricePolicy
import movie.pricePolicy.PricePolicyInfo
import java.time.LocalDateTime

class Cinema {
    fun reserveMovieTicket(screening: Screening, peopleCount: TicketCount, reserveTime: LocalDateTime): Ticket {
        return Ticket(
            title = screening.title,
            reserveTime = reserveTime,
            price = getMoviePrice(NormalPricePolicy(), reserveTime),
            peopleNumber = peopleCount.toInt(),
        )
    }

    private fun getMoviePrice(pricePolicy: PricePolicy, reserveTime: LocalDateTime): Int =
        pricePolicy.calculatePrice(PricePolicyInfo(DEFAULT_MOVIE_PRICE, reserveTime)).price

    companion object {
        private const val DEFAULT_MOVIE_PRICE = 13000
    }
}
