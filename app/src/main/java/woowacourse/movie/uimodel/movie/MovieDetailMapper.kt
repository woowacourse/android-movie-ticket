package woowacourse.movie.uimodel.movie

import woowacourse.movie.model.movie.Movie

fun Movie.toMovieDetail(): MovieDetail =
    MovieDetail(
        title.format(),
        screeningPeriod.format(),
        runningTime.format(),
        synopsis.format(),
    )
