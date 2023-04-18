package woowacourse.movie.ui.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.Movie
import java.io.Serializable
import java.time.LocalDate

fun mapToMovie(movie: MovieModel): Movie {
    return Movie(
        movie.title,
        movie.startDate,
        movie.endDate,
        movie.runningTime,
        movie.description
    )
}

data class MovieModel(
    @DrawableRes
    val poster: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : Serializable
