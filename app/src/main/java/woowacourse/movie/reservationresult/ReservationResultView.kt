package woowacourse.movie.reservationresult

import woowacourse.movie.reservationresult.uimodel.ReservationResultUiModel

interface ReservationResultView {
    fun showResult(reservationResult: ReservationResultUiModel)
}
