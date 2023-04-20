package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatPositionState(
    val row: Int,
    val column: Int
) : Parcelable
