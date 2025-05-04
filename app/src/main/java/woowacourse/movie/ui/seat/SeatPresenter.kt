package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.reservation.PurchaseCount
import woowacourse.movie.domain.model.reservation.Reservation
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatRate
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.domain.model.ticket.Tickets

class SeatPresenter(private val view: SeatContract.View) : SeatContract.Presenter {
    private lateinit var reservation: Reservation
    private var purchaseCount: PurchaseCount? = null

    override fun initData(
        reservation: Reservation,
        purchaseCount: PurchaseCount,
    ) {
        initReservation(reservation)
        initPurchaseCount(purchaseCount)
        val totalPrice = reservation.tickets?.totalPrice() ?: 0
        view.initView(this.reservation.title, totalPrice)
        view.setReserveEnabled(reservation.ticketCount == (purchaseCount.value))
    }

    override fun addTicket(
        rowPosition: Int,
        columnPosition: Int,
        rate: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit,
    ) {
        val seat = Seat(Row(rowPosition), Column(columnPosition), SeatRate.of(rate) ?: return)
        val ticket = Ticket(seat)
        if (reservation.ticketCount >= (purchaseCount?.value ?: 0)) {
            onFailure()
            return
        }
        onSuccess()
        reservation =
            if (reservation.isEmptyTickets()) {
                reservation.initTickets(Tickets(listOf(ticket)))
            } else {
                reservation.addTicket(ticket)
            }
        val totalPrice = reservation.tickets?.totalPrice() ?: 0
        view.updatePrice(totalPrice)
        view.setReserveEnabled(reservation.ticketCount == (purchaseCount?.value ?: 0))
    }

    override fun removeTicket(
        rowPosition: Int,
        columnPosition: Int,
        rate: String,
    ) {
        val seat = Seat(Row(rowPosition), Column(columnPosition), SeatRate.of(rate) ?: return)
        val ticket = Ticket(seat)
        if (reservation.isEmptyTickets()) return
        reservation = reservation.removeTicket(ticket)
        val totalPrice = reservation.tickets?.totalPrice() ?: 0
        view.updatePrice(totalPrice)
        view.setReserveEnabled(reservation.ticketCount == (purchaseCount?.value ?: 0))
    }

    override fun reserve() {
        view.reserve(reservation)
    }

    private fun initReservation(reservation: Reservation) {
        this.reservation = reservation
    }

    private fun initPurchaseCount(purchaseCount: PurchaseCount) {
        this.purchaseCount = purchaseCount
    }
}
