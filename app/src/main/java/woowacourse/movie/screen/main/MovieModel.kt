package woowacourse.movie.screen.main

import android.content.Context
import androidx.annotation.DrawableRes
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.io.Serializable

data class MovieModel(
    val id: Long = 0,
    @DrawableRes val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
) : Serializable {
    fun getFormattedOpeningDay(context: Context): String = String.format(context.getString(R.string.opening_day), this.openingDay)

    fun getFormattedRunningTime(context: Context): String = String.format(context.getString(R.string.running_time), this.runningTime)
}

fun Movie.toUiModel() =
    MovieModel(
        id = this.id,
        poster = R.drawable.poster,
        title = this.title,
        content = this.content,
        openingDay = this.openingDay,
        runningTime = this.runningTime,
    )
