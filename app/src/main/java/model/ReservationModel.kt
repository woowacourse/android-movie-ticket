package model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

class ReservationModel(
    val title: String,
    val runTime: Int,
    val summary: String,
    val startDate: LocalDate,
    val endDate: LocalDate,

    @DrawableRes
    val poster: Int,
) : Serializable {
    constructor(MovieListItem: MovieListItem) : this(
        title = MovieListItem.title,
        runTime = MovieListItem.runTime,
        summary = MovieListItem.summary,
        startDate = MovieListItem.startDate,
        endDate = MovieListItem.endDate,
        poster = MovieListItem.poster,
    )

    companion object {
        val EMPTY = ReservationModel("", 0, "", LocalDate.MIN, LocalDate.MIN, 0)
    }
}
