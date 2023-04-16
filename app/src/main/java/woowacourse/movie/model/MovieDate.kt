package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDate(
    val year: Int,
    val month: Int,
    val day: Int,
) : Parcelable
