package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Column
import woowacourse.movie.domain.model.PurchaseCount
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Row
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatRate
import woowacourse.movie.domain.model.Seats

class SeatPresenter(private val view: SeatContract.View) : SeatContract.Presenter {
    private lateinit var seats: Seats
    private lateinit var reservation: Reservation
    private var purchaseCount: PurchaseCount? = null

    override fun initData(
        rowRate: List<String>,
        columnsSize: List<Int>,
        reservation: Reservation,
        purchaseCount: PurchaseCount,
    ) {
        initSeats(rowRate, columnsSize)
        initReservation(reservation)
        initPurchaseCount(purchaseCount)
        view.initView(this.reservation.title, reservation.tickets)
    }

    private fun initSeats(
        rowRate: List<String>,
        columnsSize: List<Int>,
    ) {
        val seats =
            rowRate.mapIndexed { rowIndex, rate ->
                val row = Row(rowIndex)
                val seatRate = SeatRate.of(rate)
                if (seatRate == SeatRate.NONE) return
                List(columnsSize[rowIndex]) { columnIndex ->
                    Seat(row, Column(columnIndex), seatRate)
                }
            }.flatten()
        this.seats = Seats(seats)
    }

    private fun initReservation(reservation: Reservation) {
        this.reservation = reservation
    }

    private fun initPurchaseCount(purchaseCount: PurchaseCount) {
        this.purchaseCount = purchaseCount
    }
}
