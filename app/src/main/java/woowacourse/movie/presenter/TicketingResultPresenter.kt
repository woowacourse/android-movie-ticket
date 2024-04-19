package woowacourse.movie.presenter

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Result
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
    movieId: Int,
    private val count: Int,
    private val totalPrice: Int,
) : TicketingResultContract.Presenter {
    private val movie = MovieRepository.findMovieById(movieId)

    override fun assignInitialView() {
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
