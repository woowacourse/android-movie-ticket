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
            title: String,
            screenDate: String,
            intent: Intent,
        )

        fun ticketCount(): Int
    }
}
