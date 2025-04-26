package woowacourse.movie.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieSeatUiModel(
    val row: Int,
    val column: Int,
    val seatType: SeatTypeUiModel,
    val isSelected: Boolean,
) : Parcelable
