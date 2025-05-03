package woowacourse.movie.feature.seatSelect

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatIndexData(
    val row: Int,
    val col: Int,
) : Parcelable
