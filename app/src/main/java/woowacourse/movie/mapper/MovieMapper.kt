package woowacourse.movie.mapper

import com.example.domain.model.model.Movie
import woowacourse.movie.formatter.DateFormatter
import woowacourse.movie.model.MovieModel

const val movieDateFormat: String = "yyyy.M.d"

fun MovieModel.toMovie() = Movie(
    image,
    title,
    DateFormatter.formatToOriginal(startDate, movieDateFormat),
    DateFormatter.formatToOriginal(endDate, movieDateFormat),
    runningTime,
    description
)

fun Movie.toMovieModel() = MovieModel(
    image,
    title,
    DateFormatter.formatToString(startDate, movieDateFormat),
    DateFormatter.formatToString(endDate, movieDateFormat),
    runningTime,
    description
)
