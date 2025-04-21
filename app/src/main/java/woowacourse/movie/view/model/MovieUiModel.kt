package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod

@Parcelize
data class MovieUiModel(
    val title: String,
    val poster: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Parcelable

fun Movie.toUiModel(): MovieUiModel = MovieUiModel(title, poster, screeningPeriod, runningTime.minute)

fun MovieUiModel.toModel(): Movie = Movie(title, poster, screeningPeriod, RunningTime(runningTime))
