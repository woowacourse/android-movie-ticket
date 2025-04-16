package woowacourse.movie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val posterResId: Int,
    val title: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: String,
) : Parcelable
