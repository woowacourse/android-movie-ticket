package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

sealed class DisplayItem : Serializable {

    data class MovieInfo(
        val movieName: String,
        @DrawableRes val posterImage: Int?,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val screeningPeriod: List<LocalDate>,
        val runningTime: Int,
        val description: String
    ) : DisplayItem()

    data class Advertisement(
        @DrawableRes val adImageSrc: Int?,
        val url: String,
    ) : DisplayItem()
}
