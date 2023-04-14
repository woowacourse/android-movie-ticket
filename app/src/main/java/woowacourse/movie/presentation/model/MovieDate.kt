package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieDate(
    val year: Int,
    val month: Int,
    val day: Int,
) : Parcelable
