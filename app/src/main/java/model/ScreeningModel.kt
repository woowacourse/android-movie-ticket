package model

import java.io.Serializable
import java.time.LocalDate

class ScreeningModel(
    val movieModel: MovieModel,
    val reservationModel: ReservationModel,
) : ItemRecycler, Serializable {
    val title: String = movieModel.title
    val runTime: Int = movieModel.runTime
    val summary: String = movieModel.summary
    val poster: Int = movieModel.poster

    val startDate: LocalDate = reservationModel.startDate
    val endDate: LocalDate = reservationModel.endDate

    companion object {
        val EMPTY = ScreeningModel(MovieModel.EMPTY, ReservationModel.EMPTY)
    }
}
