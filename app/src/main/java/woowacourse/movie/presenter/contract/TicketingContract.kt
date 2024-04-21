package woowacourse.movie.presenter.contract

import woowacourse.movie.model.Movie

interface TicketingContract {
    interface View {
        fun assignInitialView(
            movie: Movie,
            count: Int,
        )

        fun updateCount(count: Int)

        fun navigateToTicketingResult(
            movieId: Int,
            count: Int,
            totalPrice: Int,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeTicketingData()

        fun decreaseCount()

        fun increaseCount()

        fun reserveTickets()
    }
}
