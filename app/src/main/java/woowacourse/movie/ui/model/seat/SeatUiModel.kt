package woowacourse.movie.ui.model.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeatUiModel(
    val row: RowUiModel,
    val col: ColUiModel,
) : Parcelable
