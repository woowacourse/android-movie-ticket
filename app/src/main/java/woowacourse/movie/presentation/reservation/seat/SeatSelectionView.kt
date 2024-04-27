package woowacourse.movie.presentation.reservation.seat

import woowacourse.movie.presentation.reservation.seat.model.SeatBoardUiModel
import woowacourse.movie.presentation.reservation.seat.model.SeatUiModel

interface SeatSelectionView {
    fun showMovieTitle(title: String)

    fun showSeat(seat: SeatUiModel)

    fun showSeatBoard(board: SeatBoardUiModel)

    fun showTotalPrice(price: Long)

    fun showSelectionError()

    fun activateReservationButton()

    fun deactivateReservationButton()

    fun navigateToReservationResult(reservationId: Long)
}
