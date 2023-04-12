package woowacourse.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.datetime.ScreeningPeriod

@Parcelize
data class MovieData(
    val posterImage: Int,
    val title: String,
    val screeningDay: ScreeningPeriod,
    val runningTime: Int,
    val description: String = ""
) : Parcelable
