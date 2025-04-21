package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Poster
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod

@Parcelize
data class MovieUiModel(
    val id: Long,
    val title: String,
    val poster: Poster,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Parcelable

fun Movie.toUiModel(): MovieUiModel = MovieUiModel(id, title, poster, screeningPeriod, runningTime.minute)

fun MovieUiModel.toModel(): Movie = Movie(id, title, poster, screeningPeriod, RunningTime(runningTime))
