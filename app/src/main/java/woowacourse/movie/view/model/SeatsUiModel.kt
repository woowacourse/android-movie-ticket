package woowacourse.movie.view.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SeatsUiModel(
    val capacity: Int,
    val seats: List<SeatUiModel>,
) : Parcelable
