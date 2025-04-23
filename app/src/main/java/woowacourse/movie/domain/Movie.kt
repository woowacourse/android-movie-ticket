package woowacourse.movie.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @DrawableRes val image: Int,
    val title: String,
    val date: Date,
    val time: Int,
) : Parcelable
