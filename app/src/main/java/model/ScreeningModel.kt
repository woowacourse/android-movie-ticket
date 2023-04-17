package model

import movie.TicketCount
import movie.pricePolicy.NormalPricePolicy
import movie.pricePolicy.PricePolicy
import movie.pricePolicy.PricePolicyInfo
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

class ScreeningModel(
    private val movieModel: MovieModel,
    private val reservationModel: ReservationModel,
) : Serializable {
    val title: String = movieModel.title
    val runningTime: Int = movieModel.runningTime
    val summary: String = movieModel.summary
    val poster: Int = movieModel.poster

    val startDate: LocalDate = reservationModel.startDate
    val endDate: LocalDate = reservationModel.endDate

    fun reserve(peopleCount: TicketCount, reserveTime: LocalDateTime): TicketModel {
        val movieTicketPeople = (0 until peopleCount.toInt())
            .map { MovieTicketPersonModel(getMoviePrice(NormalPricePolicy(), reserveTime)) }

        return TicketModel(
            title = movieModel.title,
            reserveTime = reserveTime,
            people = movieTicketPeople,
        )
    }

    fun getReserveDateRange(): String = reservationModel.getReserveDateRange()

    private fun getMoviePrice(pricePolicy: PricePolicy, reserveTime: LocalDateTime): Int =
        pricePolicy(PricePolicyInfo(DEFAULT_MOVIE_PRICE, reserveTime)).price

    companion object {
        private const val DEFAULT_MOVIE_PRICE = 13000
        val EMPTY = ScreeningModel(MovieModel.EMPTY, ReservationModel.EMPTY)
    }
}
