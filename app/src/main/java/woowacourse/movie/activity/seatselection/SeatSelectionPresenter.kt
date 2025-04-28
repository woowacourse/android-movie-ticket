package woowacourse.movie.activity.seatselection

import android.widget.TextView
import woowacourse.movie.domain.Ticket

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
) : SeatSelectionContract.Presenter {
    private var audienceCount: Int = 0
    private var ticketPrice: Int = 0

    override fun loadMovie(ticket: Ticket) {
        view.showMovieInfo(ticket)
    }

    override fun calculateMoney(
        rowIndex: Int,
        isSelected: Boolean,
    ) {
        val oneTicketPrice =
            when (rowIndex) {
                0, 1 -> B_CLASS_SEAT_PRICE
                2, 3 -> S_CLASS_SEAT_PRICE
                else -> A_CLASS_SEAT_PRICE
            }

        when (isSelected) {
            true -> ticketPrice += oneTicketPrice
            false -> ticketPrice -= oneTicketPrice
        }

        view.showMoney(ticketPrice)
    }

    override fun calculateAudienceCount(isSelected: Boolean): Int {
        when (isSelected) {
            true -> audienceCount++
            false -> audienceCount--
        }
        return audienceCount
    }

    override fun selectSeat(
        seat: TextView,
        ticket: Ticket,
    ): Boolean {
        seat.isSelected = !seat.isSelected
        return seat.isSelected
    }

    override fun handleConfirmButtonActivation(seats: Sequence<Sequence<TextView>>) {
        val hasSelection = seats.flatten().any { it.isSelected }
        view.updateConfirmButtonState(hasSelection)
    }

    override fun confirmSeatSelection(
        seats: Sequence<Sequence<TextView>>,
        ticket: Ticket,
    ) {
        val selectedSeats =
            seats
                .flatten()
                .filter { it.isSelected }
                .map { it.text.toString() }
                .toList()

        val updatedTicket =
            ticket.copy(
                count = audienceCount,
                money = ticketPrice,
                seat = selectedSeats,
            )

        view.moveToBookingResult(updatedTicket)
    }

    companion object {
        private const val B_CLASS_SEAT_PRICE = 10000
        private const val S_CLASS_SEAT_PRICE = 15000
        private const val A_CLASS_SEAT_PRICE = 12000
    }
}
