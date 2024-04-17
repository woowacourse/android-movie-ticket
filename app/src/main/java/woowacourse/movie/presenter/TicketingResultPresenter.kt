package woowacourse.movie.presenter

import woowacourse.movie.MOVIES
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract,
    private val movieId: Int,
    private val numberOfPeople: Int,
) {
    private val movie = MOVIES.find { it.id == movieId }
    private val price = MOVIE_PRICE * numberOfPeople

    fun assignInitialView() {
        movie?.let {
            ticketingResultView.assignInitialView(numberOfPeople, movie.title, movie.date, price)
        }
    }

    companion object {
        private const val MOVIE_PRICE = 13000
    }
}
