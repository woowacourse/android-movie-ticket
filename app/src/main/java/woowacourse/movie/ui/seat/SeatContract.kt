package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.reservation.PurchaseCount
import woowacourse.movie.domain.model.reservation.Reservation

interface SeatContract {
    interface Presenter {
        fun initData(
            reservation: Reservation,
            purchaseCount: PurchaseCount,
        )

        fun addTicket(
            rowPosition: Int,
            columnPosition: Int,
            rate: String,
            onSuccess: () -> Unit,
            onFailure: () -> Unit,
        )

        fun removeTicket(
            rowPosition: Int,
            columnPosition: Int,
            rate: String,
        )

        fun reserve()
    }

    interface View {
        fun initView(
            title: String,
            totalPrice: Int,
        )

        fun updatePrice(totalPrice: Int)

        fun setReserveEnabled(isMatchPurchaseCount: Boolean)

        fun reserve(reservation: Reservation)
    }
}
