package woowacourse.movie.ui

import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenAd

fun Screen.toPreviewUI(image: Image<Any>) =
    ScreenAd.ScreenPreviewUi(
        id = id,
        moviePreviewUI = movie.toPreviewUI(image),
        dateRange = dateRange,
    )

fun Movie.toPreviewUI(image: Image<Any>) =
    MoviePreviewUI(
        title = title,
        image = image,
        runningTime = runningTime,
    )

fun Screen.toDetailUI(image: Image<Any>) =
    ScreenDetailUI(
        id = id,
        movieDetailUI =
            MovieDetailUI(
                title = movie.title,
                runningTime = movie.runningTime,
                description = movie.description,
                image = image,
            ),
        dateRange = dateRange,
    )

fun DateRange.toUi(): String = "$start ~ $endInclusive"
