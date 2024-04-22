package woowacourse.movie.reservationresult

import woowacourse.movie.reservationresult.ReservationResultUiModel

interface ReservationResultView {
    fun showResult(reservationResult: ReservationResultUiModel)
}
