package woowacourse.movie.view

import woowacourse.movie.model.ReservationResultUiModel

interface ReservationResultView {
    fun showResult(reservationResult: ReservationResultUiModel)
}
