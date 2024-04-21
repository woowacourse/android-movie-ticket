package woowacourse.movie.presenter

import woowacourse.movie.findMovieById
import woowacourse.movie.model.Result
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
) : TicketingResultContract.Presenter {
    override fun initializeTicketingResult(
        movieId: Int,
        count: Int,
        totalPrice: Int,
    ) {
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
                ticketingResultView.showToastMessage(movie.message)
            }
        }
    }
}
