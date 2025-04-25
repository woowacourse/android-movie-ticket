package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.cinema.seat.SeatType

@Parcelize
enum class SeatTypeUiModel : Parcelable {
    S_CLASS,
    A_CLASS,
    B_CLASS,
}

fun SeatType.toUiModel(): SeatTypeUiModel = SeatTypeUiModel.valueOf(this.toString())

fun SeatTypeUiModel.toModel(): SeatType = SeatType.valueOf(this.toString())
