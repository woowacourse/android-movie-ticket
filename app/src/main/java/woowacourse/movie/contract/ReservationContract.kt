package woowacourse.movie.contract

import android.content.Intent

interface ReservationContract {
    interface View {
        fun setUpView()

        fun updateTicketCount()
    }

    interface Presenter {
        fun subTicketCount()

        fun addTicketCount()

        fun clickReservationCompleteButton(
            intent: Intent,
            title: String,
            screenDate: String,
            count: String,
        )

        fun ticketCount(): Int
    }
}
