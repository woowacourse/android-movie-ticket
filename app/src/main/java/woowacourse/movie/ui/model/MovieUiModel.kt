package woowacourse.movie.ui.model

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.util.PosterMapper
import java.io.Serializable
import java.time.LocalDate

data class MovieUiModel(
    val title: String,
    val startScreeningDate: String,
    val endScreeningDate: String,
    val runningTime: String,
    @DrawableRes val posterResId: Int,
) : Serializable

fun Movie.toUiModel(): MovieUiModel {
    return MovieUiModel(
        title = title,
        startScreeningDate = startScreeningDate.toString(),
        endScreeningDate = endScreeningDate.toString(),
        runningTime = runningTime.toString(),
        posterResId = PosterMapper.convertTitleToResId(title)
    )
}

fun MovieUiModel.toModel(): Movie {
    return Movie(
        title = title,
        startScreeningDate = LocalDate.parse(startScreeningDate),
        endScreeningDate = LocalDate.parse(endScreeningDate),
        runningTime = runningTime.toIntOrNull() ?: -1,
    )
}