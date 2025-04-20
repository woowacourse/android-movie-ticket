package woowacourse.movie.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: Title,
    @DrawableRes val poster: Int,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Parcelable
