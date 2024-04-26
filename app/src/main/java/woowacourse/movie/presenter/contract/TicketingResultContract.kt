package woowacourse.movie.presenter.contract

import java.time.LocalDate

interface TicketingResultContract {
    interface View {
        fun assignInitialView(
            numberOfPeople: Int,
            movieTitle: String,
            movieDate: LocalDate,
            totalPrice: Int,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeTicketingResult(
            screeningId: Long,
            count: Int,
            totalPrice: Int,
        )
    }
}
