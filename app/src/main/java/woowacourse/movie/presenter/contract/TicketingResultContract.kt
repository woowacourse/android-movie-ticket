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
            movieId: Int,
            count: Int,
            totalPrice: Int,
        )
    }
}
