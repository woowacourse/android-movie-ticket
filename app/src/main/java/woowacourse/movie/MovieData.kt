package woowacourse.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
    val posterImage: Int,
    val title: String,
    val screeningDay: String,
    val runningTime: Int
) : Parcelable
