package woowacourse.movie.seat

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.booking.detail.TicketUiModel
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Ticket

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val ticket: TicketUiModel?,
) : SeatSelectionContract.Presenter {
    private lateinit var domainTicket: Ticket

    override fun initializeData(savedInstanceState: Bundle?) {
        if (ticket == null) {
            view.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.")
            return
        }

        domainTicket = ticket.toDomain()
        view.showTicket(ticket)
    }

    override fun onSeatClicked(seat: TextView) {
        val seatName = seat.text.toString()
        val domainSeat = Seat(seatName)

        domainTicket = domainTicket.toggleSeat(domainSeat)

        val isSelected = domainTicket.seats.values.any { it.seatName == seatName }
        view.showSeatState(seat, isSelected)

        val shouldEnableButton = domainTicket.seats.values.size == domainTicket.headCount.value
        view.setButtonEnabled(shouldEnableButton)

        view.showTicket(domainTicket.toUiModel())
    }

    override fun onButtonClicked() {
        val ticketUiModel = domainTicket.toUiModel()
        view.showBookingAlertDialog(ticketUiModel)
    }
}
