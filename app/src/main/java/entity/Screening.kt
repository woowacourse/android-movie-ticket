package entity

import movie.TicketCount
import movie.pricePolicy.NormalPricePolicy
import movie.pricePolicy.PricePolicy
import movie.pricePolicy.PricePolicyInfo
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

class Screening(
    private val movie: Movie,
    private val reservation: Reservation,
) : MovieListType, Serializable {
    val title: String = movie.title
    val runningTime: Int = movie.runningTime
    val summary: String = movie.summary
    val poster: Int = movie.poster

    val startDate: LocalDate = reservation.startDate
    val endDate: LocalDate = reservation.endDate

    fun reserve(peopleCount: TicketCount, reserveTime: LocalDateTime): MovieTicket {
        val movieTicketPeople = (0 until peopleCount.toInt())
            .map { MovieTicketPerson(getMoviePrice(NormalPricePolicy(), reserveTime)) }

        return MovieTicket(
            title = movie.title,
            reserveTime = reserveTime,
            people = movieTicketPeople,
        )
    }

    fun getReserveDateRange(): String = reservation.getReserveDateRange()

    private fun getMoviePrice(pricePolicy: PricePolicy, reserveTime: LocalDateTime): Int =
        pricePolicy(PricePolicyInfo(DEFAULT_MOVIE_PRICE, reserveTime)).price

    companion object {
        private const val DEFAULT_MOVIE_PRICE = 13000
    }
}
