package woowacourse.movie.domain.movie

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.datetime.ScreeningPeriod

@Parcelize
data class MovieData(
    @DrawableRes val posterImage: Int,
    val title: String,
    val screeningDay: ScreeningPeriod,
    val runningTime: Int,
    val description: String = ""
) : Parcelable
