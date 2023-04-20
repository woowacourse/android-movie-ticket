package model

import java.io.Serializable
import java.time.LocalDate

class MovieListItem(movie: MovieModel, val startDate: LocalDate, val endDate: LocalDate) : MovieListType, Serializable {
    val title: String = movie.title
    val runTime: Int = movie.runTime
    val summary: String = movie.summary
    val poster: Int = movie.poster
}
