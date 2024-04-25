package woowacourse.movie.presenter.contract

import woowacourse.movie.model.screening.Movie

interface TicketingContract {
    interface View {
        fun assignInitialView(
            movie: Movie,
            count: Int,
        )

        fun updateCount(count: Int)

        fun navigateToTicketingResult(
            movieId: Long,
            count: Int,
            totalPrice: Int,
            date: String,
            time: String,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeTicketingData()

        fun decreaseCount()

        fun increaseCount()

        fun reserveTickets()

        fun updateDate(date: String)

        fun updateTime(time: String)
    }
}
