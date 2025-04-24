package woowacourse.movie.view.movie.model

import woowacourse.movie.common.DummyData
import woowacourse.movie.domain.Movie

fun Movie.toUiModel(): MovieUiModel =
    MovieUiModel(
        title = title,
        startDate = startDate,
        endDate = endDate,
        runningTime = runningTime,
        posterResId = DummyData.getDrawableResId(this),
    )

fun MovieUiModel.toDomain(): Movie =
    Movie(
        title = title,
        startDate = startDate,
        endDate = endDate,
        runningTime = runningTime,
    )
