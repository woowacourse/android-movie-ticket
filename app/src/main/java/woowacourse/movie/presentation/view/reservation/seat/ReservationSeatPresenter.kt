package woowacourse.movie.presentation.view.reservation.seat

import woowacourse.movie.domain.model.cinema.DiceCinemaPricePolicy
import woowacourse.movie.domain.model.cinema.Screen
import woowacourse.movie.domain.model.cinema.ScreenSize
import woowacourse.movie.domain.model.cinema.TicketMachine
import woowacourse.movie.domain.model.cinema.ticket.TicketBundle
import woowacourse.movie.domain.model.reservation.ReservationInfo
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.SeatUiModel
import woowacourse.movie.presentation.model.toModel
import woowacourse.movie.presentation.model.toUiModel

class ReservationSeatPresenter(
    private val view: ReservationSeatContract.View,
) : ReservationSeatContract.Presenter {
    private val machine: TicketMachine = TicketMachine(DiceCinemaPricePolicy())
    private var _reservationInfo: ReservationInfo? = null
    val reservationInfo get() = _reservationInfo?.toUiModel()

    override fun fetchData(reservationInfoUiModel: ReservationInfoUiModel?) {
        reservationInfoUiModel?.let { uiModel ->
            _reservationInfo = uiModel.toModel()
            view.setScreen(
                uiModel,
                Screen.create(DEFAULT_SEAT_SIZE).toUiModel(),
                publishTicketBundle()?.totalPrice ?: 0,
            )
            return
        }

        view.showMessage("예매 정보를 불러오는데 실패했습니다.")
    }

    override fun updateSeat(seat: SeatUiModel) {
        runCatching {
            _reservationInfo?.updateSeats(seat.toModel())
        }.onFailure {
            view.showMessage(it.message.orEmpty())
        }

        view.updateSeatStatus(
            reservationInfo?.seats?.map { it } ?: emptyList(),
            publishTicketBundle()?.totalPrice ?: TicketBundle.DEFAULT_TOTAL_PRICE,
            _reservationInfo?.let { machine.canPublish(it) } ?: false,
        )
    }

    override fun publishTickets() {
        publishTicketBundle()?.let {
            view.notifyPublishedTickets(it.toUiModel())
            return
        }
    }

    private fun publishTicketBundle(): TicketBundle? {
        runCatching {
            reservationInfo?.let { machine.publishTickets(it.toModel()) }
        }.onSuccess {
            return it
        }

        return null
    }

    companion object {
        private val DEFAULT_SEAT_SIZE = ScreenSize(5, 4)
    }
}
