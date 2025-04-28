package woowacourse.movie.seating

import woowacourse.movie.domain.ReservationInfo
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.PriceFormatter

class SeatingPresenter(private val view: SeatingContract.View) : SeatingContract.Presenter {
    private lateinit var reservationInfo: ReservationInfo
    private var selectedSeats: MutableSet<Seat> = mutableSetOf()
    private var selectedStringSeats: MutableSet<String> = mutableSetOf()
    private lateinit var ticket: Ticket
    private var totalPrice = DEFAULT_PRICE
    private val priceFormatter = PriceFormatter()

    override fun set(reservationInfo: ReservationInfo) {
        this.reservationInfo = reservationInfo
        view.showTitle(reservationInfo.title)

        val formattedPrice = priceFormatter.format(totalPrice)
        view.showPrice(formattedPrice)
    }

    override fun clickedSeat(seat: String) {
        val selectedSeat = Seat(seat)
        if (selectedSeats.contains(selectedSeat)) {
            selectedSeats.remove(selectedSeat)
            selectedStringSeats.remove(selectedSeat.seat)
            view.showActivateSeat()
        } else if (selectedSeats.size < reservationInfo.personnel) {
            selectedSeats.add(selectedSeat)
            selectedStringSeats.add(selectedSeat.seat)
        } else {
            view.showDeactivateSeat(selectedStringSeats)
        }

        totalPrice = selectedSeats.sumOf { it.price() }
        val formattedPrice = priceFormatter.format(totalPrice)
        view.showPrice(formattedPrice)
        if (selectedSeats.size > 0) {
            this.ticket = Ticket(reservationInfo, selectedStringSeats.toSet(), this.totalPrice)
            view.showActivateButton(ticket)
        } else {
            view.showDeactivateButton()
        }
    }

    fun canSelectMoreSeat(): Boolean {
        return selectedSeats.size < reservationInfo.personnel
    }

    companion object {
        private const val DEFAULT_PRICE = 0
    }
}
