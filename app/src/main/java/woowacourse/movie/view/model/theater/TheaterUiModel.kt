package woowacourse.movie.view.model.theater

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class TheaterUiModel(
    val capacity: Int,
    val seats: List<SeatUiModel>,
) : Parcelable
