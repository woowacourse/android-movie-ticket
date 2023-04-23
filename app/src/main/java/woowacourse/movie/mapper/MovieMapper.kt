package woowacourse.movie.mapper

import woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieListModel

fun MovieListModel.MovieModel.toDomain(): Movie = Movie(
    title = title,
    startDate = startDate,
    endDate = endDate,
    runningTime = runningTime,
    description = description
)
