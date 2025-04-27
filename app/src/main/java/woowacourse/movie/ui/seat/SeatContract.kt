package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.PurchaseCount
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Tickets

interface SeatContract {
    interface Presenter {
        fun initData(
            rowRate: List<String>,
            columnsSize: List<Int>,
            reservation: Reservation,
            purchaseCount: PurchaseCount,
        )
    }

    interface View {
        fun initView(
            title: String,
            tickets: Tickets?,
        )
    }
}
