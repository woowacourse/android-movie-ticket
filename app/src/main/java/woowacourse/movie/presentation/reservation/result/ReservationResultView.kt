package woowacourse.movie.presentation.reservation.result

interface ReservationResultView {
    fun showResult(reservationResult: ReservationResultUiModel)

    fun showErrorView()
}
