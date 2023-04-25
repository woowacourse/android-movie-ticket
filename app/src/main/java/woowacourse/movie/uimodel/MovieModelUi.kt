package woowacourse.movie.uimodel

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

sealed interface MovieModelUi : Serializable {
    class MovieScheduleUi(
        val title: String,
        val runningTime: Int,
        val summary: String,
        val startDate: LocalDate,
        val endDate: LocalDate,
        @DrawableRes val poster: Int,
    ) : Serializable, MovieModelUi {
        companion object {
            val EMPTY = MovieScheduleUi(
                title = "",
                runningTime = 0,
                summary = "",
                startDate = LocalDate.MIN,
                endDate = LocalDate.MIN,
                poster = 0,
            )
        }
    }

    class AdUi(
        @DrawableRes val adPoster: Int,
    ) : Serializable, MovieModelUi
}
