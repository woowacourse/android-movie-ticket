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

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun assignInitialView()

        fun decreaseCount()

        fun increaseCount()

        fun navigate()
    }
}
