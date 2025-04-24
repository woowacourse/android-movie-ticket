package woowacourse.movie.movies

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Parcelize
data class MovieUiModel(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTimeText: String,
    val posterResId: Int,
) : Parcelable

fun MovieUiModel.periodText(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    return "${startDate.format(formatter)} ~ ${endDate.format(formatter)}"
}

fun Movie.toMovieUiModel(): MovieUiModel {
    val movieUiModel =
        MovieUiModel(
            title = this.title,
            startDate = this.startDate,
            endDate = this.endDate,
            runningTimeText = "${runningTime}분",
            posterResId = this.poster,
        )
    return movieUiModel
}
