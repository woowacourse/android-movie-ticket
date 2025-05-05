package woowacourse.movie

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.feature.mapper.toUi

val MOVIE_UI_MODEL_01 =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = MovieDate(2025, 4, 1),
        endDate = MovieDate(2025, 4, 25),
        runningTime = 152,
    ).toUi()
