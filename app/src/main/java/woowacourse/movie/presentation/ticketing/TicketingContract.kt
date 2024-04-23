package woowacourse.movie.presentation.ticketing

import woowacourse.movie.model.Movie

interface TicketingContract {
    interface View {
        fun assignInitialView(
            movie: Movie,
            count: Int,
        )

        fun updateCount(count: Int)

        fun navigate(
            movieId: Int,
            count: Int,
            totalPrice: Int,
        )

        fun showErrorMessage(message: String?)
    }

    interface Presenter {
        fun assignInitialView()

        fun decreaseCount()

        fun increaseCount()

        fun navigate()
    }
}
