package woowacourse.movie.mapper

import woowacourse.movie.domain.Movie
import woowacourse.movie.model.MovieModel

fun Movie.toModel(): MovieModel = MovieModel(
    poster = poster,
    title = title,
    startDate = startDate,
    endDate = endDate,
    runningTime = runningTime,
    description = description
)

fun MovieModel.toDomain(): Movie = Movie(
    poster = poster,
    title = title,
    startDate = startDate,
    endDate = endDate,
    runningTime = runningTime,
    description = description
)
