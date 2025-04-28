package woowacourse.movie.presentation.view.reservation.seat

import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.ScreenUiModel
import woowacourse.movie.presentation.model.SeatUiModel
import woowacourse.movie.presentation.model.TicketBundleUiModel

interface ReservationSeatContract {
    interface Presenter {
        fun fetchData(
            reservationInfoUiModel: ReservationInfoUiModel?,
            screen: ScreenUiModel?,
        )

        fun updateSeat(seat: SeatUiModel)

        fun publishTickets()
    }

    interface View {
        fun setScreen(
            reservationInfo: ReservationInfoUiModel,
            screen: ScreenUiModel,
            selectedSeats: List<SeatUiModel>,
            totalPrice: Int,
            canPublish: Boolean,
        )

        fun updateSeatState(
            selectedSeat: SeatUiModel,
            totalPrice: Int,
            canPublish: Boolean,
        )

        fun notifyPublishedTickets(ticketBundle: TicketBundleUiModel)

        fun notifyInvalidReservationInfo()

        fun notifySeatUpdateFailed(message: String)
    }
}
