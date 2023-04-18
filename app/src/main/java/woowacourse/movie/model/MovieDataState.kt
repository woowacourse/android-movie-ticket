package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDataState(
    val posterImage: Int,
    val title: String,
    val screeningDay: ScreeningPeriodState,
    val runningTime: Int,
    val description: String = ""
) : Parcelable
