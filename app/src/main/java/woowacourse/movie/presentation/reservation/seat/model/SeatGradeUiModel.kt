package woowacourse.movie.presentation.reservation.seat.model

import androidx.annotation.ColorRes
import woowacourse.movie.R

enum class SeatGradeUiModel(
    @ColorRes val textColor: Int,
) {
    B(R.color.purple_500),
    A(R.color.blue_700),
    S(R.color.green_500),
}
