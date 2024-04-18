package woowacourse.movie.presenter.contract

interface TicketingResultContract {
    fun assignInitialView(
        numberOfPeople: Int,
        movieTitle: String,
        movieDate: String,
        price: Int,
    )

    fun showErrorMessage(message: String)
}
