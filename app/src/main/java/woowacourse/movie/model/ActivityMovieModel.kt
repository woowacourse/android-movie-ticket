package woowacourse.movie.model

import androidx.annotation.DrawableRes
import domain.movie.Movie
import domain.movie.MovieName
import domain.movie.RunningTime
import domain.movie.ScreeningDate
import domain.movie.ScreeningPeriod
import java.io.Serializable
import java.time.LocalDate

data class ActivityMovieModel(
    val movieName: String,
    @DrawableRes val posterImage: Int?,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val screeningPeriod: List<LocalDate>,
    val runningTime: Int,
    val description: String
) : Serializable

fun Movie.toActivityModel(posterImage: Int?) = ActivityMovieModel(
    movieName.value,
    posterImage,
    screeningPeriod.startDate.value,
    screeningPeriod.endDate.value,
    screeningPeriod.getScreeningDates().map { it.value },
    runningTime.value,
    description
)

fun ActivityMovieModel.toDomainModel() = Movie(
    MovieName(movieName),
    ScreeningPeriod(ScreeningDate(startDate), ScreeningDate(endDate)),
    RunningTime(runningTime),
    description
)
