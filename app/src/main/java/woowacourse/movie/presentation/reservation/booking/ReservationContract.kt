package woowacourse.movie.presentation.reservation.booking

import android.content.Intent

interface ReservationContract {
    interface View {
        fun setUpView(
            img: Int,
            title: String,
            screenDate: String,
            runningTime: Int,
            description: String,
        )

        fun updateTicketCount()
    }

    interface Presenter {
        fun fetchMovieDetail(movieId: Int)

        fun subTicketCount()

        fun addTicketCount()

        fun clickReservationCompleteButton(intent: Intent)

        fun ticketCount(): Int

        fun totalTicketPrice(): Int

        fun restoreTicketCount(count: Int)
    }
}
