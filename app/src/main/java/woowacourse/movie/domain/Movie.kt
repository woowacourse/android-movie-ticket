package woowacourse.movie.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(
    @DrawableRes
    val posterId: Int,
    val title: String,
    val runningTime: Int,
) : Parcelable
