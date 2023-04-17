package model

import java.io.Serializable
import java.time.LocalDate

class ScreeningModel(
    val movieModel: MovieModel,
    val reservationModel: ReservationModel,
) : Serializable {
    val title: String = movieModel.title
    val runningTime: Int = movieModel.runningTime
    val summary: String = movieModel.summary
    val poster: Int = movieModel.poster

    val startDate: LocalDate = reservationModel.startDate
    val endDate: LocalDate = reservationModel.endDate

    fun getReserveDateRange(): String = reservationModel.getReserveDateRange()

    companion object {
        val EMPTY = ScreeningModel(MovieModel.EMPTY, ReservationModel.EMPTY)
    }
}
