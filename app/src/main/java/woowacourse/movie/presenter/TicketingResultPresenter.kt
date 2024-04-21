package woowacourse.movie.presenter

import woowacourse.movie.findMovieById
import woowacourse.movie.model.Result
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
    private val count: Int,
    private val totalPrice: Int,
) : TicketingResultContract.Presenter {
    override fun initializeTicketingResult(movieId: Int) {
        when (val movie = findMovieById(movieId)) {
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
