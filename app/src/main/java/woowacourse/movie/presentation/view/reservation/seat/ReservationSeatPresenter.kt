package woowacourse.movie.presentation.view.reservation.seat

import woowacourse.movie.domain.model.cinema.DiceCinemaPricePolicy
import woowacourse.movie.domain.model.cinema.screen.Screen
import woowacourse.movie.domain.model.cinema.ticket.TicketBundle
import woowacourse.movie.domain.model.cinema.ticket.TicketMachine
import woowacourse.movie.domain.model.reservation.ReservationInfo
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.ScreenUiModel
import woowacourse.movie.presentation.model.SeatUiModel
import woowacourse.movie.presentation.model.toModel
import woowacourse.movie.presentation.model.toUiModel

class ReservationSeatPresenter(
    private val view: ReservationSeatContract.View,
) : ReservationSeatContract.Presenter {
    private val machine: TicketMachine = TicketMachine(DiceCinemaPricePolicy())
    private var _reservationInfo: ReservationInfo? = null
    val reservationInfo get() = _reservationInfo?.toUiModel()

    override fun fetchData(
        reservationInfoUiModel: ReservationInfoUiModel?,
        screen: ScreenUiModel?,
    ) {
        reservationInfoUiModel?.let { uiModel ->
            _reservationInfo = uiModel.toModel()
            view.setScreen(
                uiModel,
                screen ?: Screen.DEFAULT_SCREEN.toUiModel(),
                _reservationInfo?.seats?.map { it.toUiModel() } ?: emptyList(),
                publishTicketBundle()?.totalPrice ?: 0,
                canPublish(),
            )
            return
        }

        view.notifyInvalidReservationInfo()
    }

    override fun updateSeat(seat: SeatUiModel) {
        runCatching {
            _reservationInfo?.updateSeats(seat.toModel())
        }.onFailure {
            view.notifySeatUpdateFailed(it.message.orEmpty())
        }

        view.updateSeatStatus(
            seat,
            publishTicketBundle()?.totalPrice ?: TicketBundle.DEFAULT_TOTAL_PRICE,
            canPublish(),
        )
    }

    override fun publishTickets() {
        publishTicketBundle()?.let {
            view.notifyPublishedTickets(it.toUiModel())
            return
        }
    }

    private fun canPublish(): Boolean = _reservationInfo?.let { machine.canPublish(it) } ?: false

    private fun publishTicketBundle(): TicketBundle? {
        runCatching {
            reservationInfo?.let { machine.publishTickets(it.toModel()) }
        }.onSuccess {
            return it
        }

        return null
    }
}
