package woowacourse.movie.presenter.contract

interface TicketingResultContract {
    interface View {
        fun assignInitialView(
            numberOfPeople: Int,
            movieTitle: String,
            movieDate: String,
            movieTime: String,
            totalPrice: Int,
            seats: List<String>,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun initializeTicketingResult(
            screeningId: Long,
            count: Int,
            totalPrice: Int,
            date: String,
            time: String,
            seats: Array<String>,
        )
    }
}
