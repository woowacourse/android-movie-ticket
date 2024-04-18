package woowacourse.movie.presenter.contract

interface TicketingResultContract {
    interface View {
        fun assignInitialView(
            numberOfPeople: Int,
            movieTitle: String,
            movieDate: String,
            price: Int,
        )

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun assignInitialView()
    }
}
