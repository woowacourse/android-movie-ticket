package woowacourse.movie.contract

import woowacourse.movie.model.screening.Screening

interface ScreeningDetailContract {
    interface View {
        fun displayScreening(screening: Screening)

        fun displayTicketNum(ticketNum: Int)

        fun navigateToPurchaseConfirmation(reservationId: Int)
    }

    interface Presenter {
        fun loadScreening(screeningId: Int)

        fun plusTicketNum(ticketNum: Int)

        fun minusTicketNum(ticketNum: Int)

        fun purchase(
            screeningId: Int,
            ticketNum: Int,
        )
    }
}
