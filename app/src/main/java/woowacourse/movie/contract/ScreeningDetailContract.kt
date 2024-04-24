package woowacourse.movie.contract

import woowacourse.movie.model.Reservation
import woowacourse.movie.model.screening.Screening

interface ScreeningDetailContract {
    interface View {
        fun displayScreening(screening: Screening)

        fun displayTicketNum(ticketNum: Int)

        fun navigateToPurchaseConfirmation(reservation: Reservation)
    }

    interface Presenter {
        fun loadScreening()
        fun plusTicketNum()

        fun minusTicketNum()

        fun purchase()
    }
}
