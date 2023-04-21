package woowacourse.movie.uimodel

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

sealed class MovieModelUi : Serializable {
    class MovieScheduleUi(
        val title: String,
        val runningTime: Int,
        val summary: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        @DrawableRes val poster: Int,
    ) : Serializable, MovieModelUi()

    class AdUi(
        @DrawableRes val addPoster: Int,
    ) : Serializable, MovieModelUi()
}
