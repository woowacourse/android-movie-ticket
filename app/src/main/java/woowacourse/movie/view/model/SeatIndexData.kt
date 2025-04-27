package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatIndexData(
    val row: Int,
    val col: Int,
) : Parcelable
