package woowacourse.movie.feature.main.ui

import android.content.Context
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening
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
    ) : ScreeningItem, Serializable {
        fun getFormattedScreeningPeriod(context: Context): String =
            String.format(
                context.getString(R.string.screening_period),
                this.releaseDate,
                this.endDate,
            )

        fun getFormattedRunningTime(context: Context): String =
            String.format(
                context.getString(R.string.running_time),
                this.runningTime,
            )

        companion object {
            const val DATE_FORMAT = "yyyy.M.d"
        }
    }

    data class AdModel(
        val id: Long = 0,
        @DrawableRes val resId: Int,
    ) : ScreeningItem
}

fun Screening.toUiModel(): ScreeningItem.ScreeningModel {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(ScreeningItem.ScreeningModel.DATE_FORMAT, Locale.getDefault())
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
