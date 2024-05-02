package woowacourse.movie.feature.main.ui

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.util.DATE_FORMAT_DOT
import java.io.Serializable
import java.time.format.DateTimeFormatter
import java.util.Locale

sealed interface ScreeningItem {
    data class ScreeningModel(
        val id: Long = 0,
        @DrawableRes val poster: Int,
        val title: String,
        val content: String,
        val releaseDate: String,
        val endDate: String,
        val runningTime: Int,
    ) : ScreeningItem, Serializable

    data class AdModel(
        val id: Long = 0,
        @DrawableRes val resId: Int,
    ) : ScreeningItem
}

fun Screening.toUiModel(): ScreeningItem.ScreeningModel {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_FORMAT_DOT, Locale.getDefault())
    return ScreeningItem.ScreeningModel(
        id = id,
        poster = movie.poster,
        title = movie.title,
        content = movie.content,
        releaseDate = releaseDate.format(formatter),
        endDate = endDate.format(formatter),
        runningTime = movie.runningTime,
    )
}
