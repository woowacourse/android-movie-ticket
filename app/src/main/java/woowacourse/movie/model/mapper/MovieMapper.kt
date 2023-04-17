package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieUI

fun Movie.toMovieUI(): MovieUI =
    MovieUI(title, startDate, endDate, runningTime, introduce, thumbnail)

fun MovieUI.toMovie(): Movie = Movie(title, startDate, endDate, runningTime, introduce, thumbnail)
