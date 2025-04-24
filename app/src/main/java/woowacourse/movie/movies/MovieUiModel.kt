package woowacourse.movie.movies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie

@Parcelize
data class MovieUiModel(
    val title: String,
    val periodText: String,
    val runningTimeText: String,
    val posterResId: Int,
) : Parcelable

fun Movie.toMovieUiModel(): MovieUiModel =
    MovieUiModel(
        title = this.title,
        periodText = "$startDate ~ $endDate",
        runningTimeText = "${runningTime}분",
        posterResId = this.poster,
    )
