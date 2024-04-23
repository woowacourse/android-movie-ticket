package woowacourse.movie.presentation.ticketingResult

import java.time.LocalDate

interface TicketingResultContract {
    interface View {
        fun assignInitialView(
            numberOfPeople: Int,
            movieTitle: String,
            movieDate: LocalDate,
            price: Int,
        )

        fun showErrorMessage(message: String?)
    }

    interface Presenter {
        fun assignInitialView()
    }
}
