package woowacourse.movie.view.mapper

import com.example.domain.Minute
import com.example.domain.Movie
import woowacourse.movie.view.model.MovieUiModel

fun Movie.toUiModel(): MovieUiModel = MovieUiModel(
    title,
    screeningStartDate,
    screeningEndDate,
    runningTime.value,
    posterResourceId,
    summary
)

fun MovieUiModel.toDomainModel(): Movie = Movie(
    title,
    screeningStartDate,
    screeningEndDate,
    Minute(runningTime),
    posterResourceId,
    summary
)
