package model

import java.io.Serializable
import java.time.LocalDate

class MovieListItem(movie: MovieModel, reservation: ReservationModel) : MovieListType, Serializable {
    val title: String = movie.title
    val runTime: Int = movie.runTime
    val summary: String = movie.summary
    val poster: Int = movie.poster

    val startDate: LocalDate = reservation.startDate
    val endDate: LocalDate = reservation.endDate

    companion object {
        val EMPTY = MovieListItem(MovieModel.EMPTY, ReservationModel.EMPTY)
    }
}
