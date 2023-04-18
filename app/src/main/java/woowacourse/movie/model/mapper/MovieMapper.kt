package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieUI
import woowacourse.movie.model.utill.MovieThumbnailDrawable

fun Movie.toMovieUI(): MovieUI =
    MovieUI(id, title, startDate, endDate, runningTime, introduce, MovieThumbnailDrawable.getMovieThumbnail(id))

fun MovieUI.toMovie(): Movie = Movie(id, title, startDate, endDate, runningTime, introduce)
