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
    constructor(movie: ItemViewType.MOVIE) : this(
        title = movie.title,
        runTime = movie.runTime,
        summary = movie.summary,
        startDate = movie.startDate,
        endDate = movie.endDate,
        poster = movie.poster,
    )

    companion object {
        val EMPTY = ReservationModel("", 0, "", LocalDate.MIN, LocalDate.MIN, 0)
    }
}
