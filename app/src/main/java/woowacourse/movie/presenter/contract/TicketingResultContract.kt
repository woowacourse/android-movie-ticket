package woowacourse.movie.presenter.contract

interface TicketingResultContract {
    interface View {
        fun assignInitialView(
            numberOfPeople: Int,
            movieTitle: String,
            movieDate: String,
            price: Int,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeTicketingResult(
            movieId: Long,
            count: Int,
            totalPrice: Int,
        )
    }
}
