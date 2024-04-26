package woowacourse.movie.feature.main.ui

import android.content.Context
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.feature.reservation.ui.ReservationModel
import java.io.Serializable
import java.time.format.DateTimeFormatter
import java.util.Locale

data class MovieModel(
    val id: Long = 0,
    @DrawableRes val poster: Int,
    val title: String,
    val content: String,
    val releaseDate: String,
    val endDate: String,
    val runningTime: Int,
) : Serializable {
    fun getFormattedScreeningPeriod(context: Context): String =
        String.format(context.getString(R.string.screening_period), this.releaseDate, this.endDate)

    fun getFormattedRunningTime(context: Context): String =
        String.format(context.getString(R.string.running_time), this.runningTime)
}

fun Movie.toUiModel(): MovieModel {
    val formatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern(ReservationModel.DATE_FORMAT, Locale.getDefault())
    return MovieModel(
        id = id,
        poster = R.drawable.poster,
        title = title,
        content = content,
        releaseDate = screeningDate.releaseDate.format(formatter),
        endDate = screeningDate.endDate.format(formatter),
        runningTime = runningTime,
    )
}
