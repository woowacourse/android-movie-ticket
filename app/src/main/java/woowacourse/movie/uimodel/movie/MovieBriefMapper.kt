package woowacourse.movie.uimodel.movie

import woowacourse.movie.model.movie.Movie

fun Movie.toMovieBrief() =
    MovieBrief(
        title = title.content,
        screeningDate = "상영일: ${screeningPeriod.format()}",
        runningTime = "러닝타임: ${runningTime.format()}" + "분",
    )
