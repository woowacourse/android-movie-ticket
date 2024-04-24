package woowacourse.movie.presentation.ticketingResult

import woowacourse.movie.data.MovieRepository

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
    private val movieId: Int,
    private val count: Int,
    private val totalPrice: Int,
) : TicketingResultContract.Presenter {
    private val movieRepository = MovieRepository()

    override fun assignInitialView() {
        movieRepository.findMovieById(movieId)
            .onSuccess { movie ->
                ticketingResultView.assignInitialView(
                    count,
                    movie.title,
                    movie.date.startDate,
                    totalPrice,
                )
            }
            .onFailure { ticketingResultView.showErrorMessage(it.message) }
    }
}
