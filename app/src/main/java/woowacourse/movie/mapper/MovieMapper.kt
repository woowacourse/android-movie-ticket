package woowacourse.movie.mapper

import com.example.domain.model.model.Movie
import woowacourse.movie.formatter.DateFormatter
import woowacourse.movie.model.MovieListItem
import woowacourse.movie.model.MovieListItem.MovieModel.Companion.MOVIE_DATE_FORMAT

fun MovieListItem.MovieModel.toMovie() = Movie(
    image,
    title,
    DateFormatter.formatToOriginal(startDate, MOVIE_DATE_FORMAT),
    DateFormatter.formatToOriginal(endDate, MOVIE_DATE_FORMAT),
    runningTime,
    description
)

fun Movie.toMovieModel() = MovieListItem.MovieModel(
    image,
    title,
    DateFormatter.formatToString(startDate, MOVIE_DATE_FORMAT),
    DateFormatter.formatToString(endDate, MOVIE_DATE_FORMAT),
    runningTime,
    description
)
