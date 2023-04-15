package woowacourse.movie.presentation.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.Movie
import java.time.LocalDate

data class MovieModel(
    val id: Long,
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val thumbnail: Int?,
    @DrawableRes val poster: Int?,
)

fun Movie.toPresentation() = MovieModel(
    id = id,
    title = title,
    screeningStartDate = screeningStartDate,
    screeningEndDate = screeningEndDate,
    runningTime = runningTime,
    description = description,
    thumbnail = MovieDrawableData.getMovieThumbnail(id),
    poster = MovieDrawableData.getMoviePoster(id)
)
