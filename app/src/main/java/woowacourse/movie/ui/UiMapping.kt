package woowacourse.movie.ui

import woowacourse.movie.domain.model.Image
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen

fun Screen.toPreviewUI(image: Image<Any>) =
    ScreenPreviewUI(
        id = id,
        moviePreviewUI = movie.toPreviewUI(image),
        date = date,
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
        date = date,
    )
