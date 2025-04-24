package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod

@Parcelize
data class MovieUiModel(
    val id: Long,
    val title: String,
    val poster: PosterUiModel,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Parcelable

fun Movie.toUiModel(): MovieUiModel = MovieUiModel(id, title, poster.toUiModel(), screeningPeriod, runningTime.minute)

fun MovieUiModel.toModel(): Movie = Movie(id, title, poster.toModel(), screeningPeriod, RunningTime(runningTime))
