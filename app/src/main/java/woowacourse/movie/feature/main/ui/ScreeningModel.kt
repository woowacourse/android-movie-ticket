package woowacourse.movie.feature.main.ui

import android.content.Context
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.feature.main.ui.ScreeningModel.Companion.DATE_FORMAT
import java.io.Serializable
import java.time.format.DateTimeFormatter
import java.util.Locale

data class ScreeningModel(
    val id: Long = 0,
    @DrawableRes val poster: Int,
    val title: String,
    val content: String,
    val releaseDate: String,
    val endDate: String,
    val runningTime: Int,
) : Serializable {
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

fun Screening.toUiModel(): ScreeningModel {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.getDefault())
    return ScreeningModel(
        id = id,
        poster = movie.poster,
        title = movie.title,
        content = movie.content,
        releaseDate = releaseDate.format(formatter),
        endDate = endDate.format(formatter),
        runningTime = movie.runningTime,
    )
}
