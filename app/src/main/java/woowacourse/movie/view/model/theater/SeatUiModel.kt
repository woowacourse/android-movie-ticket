package woowacourse.movie.view.model.theater

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatUiModel(
    val row: Int,
    val col: Int,
) : Parcelable
