package woowacourse.movie.view.viewmodel

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.movieinfo.Movie
import java.io.Serializable
import java.time.LocalDate

fun Movie.toUIModel(): MovieUIModel = MovieUIModel(
    title, startDate, endDate, runningTime, description, moviePoster
)

fun MovieUIModel.toMovie(): Movie = Movie(
    title, startDate, endDate, runningTime, description, moviePoster
)

class MovieUIModel(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes
    val moviePoster: Int,
) : Serializable
