package woowacourse.movie.presenter

import woowacourse.movie.Result
import woowacourse.movie.findMovieById
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract,
    movieId: Int,
    private val count: Int,
    private val totalPrice: Int,
) {
    private val movie = findMovieById(movieId)

    fun assignInitialView() {
        when (movie) {
            is Result.Success -> {
                ticketingResultView.assignInitialView(
                    count,
                    movie.data.title,
                    movie.data.date,
                    totalPrice,
                )
            }
            is Result.Error -> {
                ticketingResultView.showErrorMessage(movie.message)
            }
        }
    }
}
