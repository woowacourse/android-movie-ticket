package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieItem
import woowacourse.movie.model.util.MovieThumbnailDrawable

fun Movie.toMovieUI(): MovieItem.MovieUI =
    MovieItem.MovieUI(
        id,
        title,
        startDate,
        endDate,
        runningTime,
        introduce,
        MovieThumbnailDrawable.getMovieThumbnail(id)
    )

fun MovieItem.MovieUI.toMovie(): Movie =
    Movie(id, title, startDate, endDate, runningTime, introduce)
