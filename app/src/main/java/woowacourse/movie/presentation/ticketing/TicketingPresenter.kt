package woowacourse.movie.presentation.ticketing

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Count
import woowacourse.movie.model.Tickets

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
    private val movieId: Int,
    savedCount: Int?,
) : TicketingContract.Presenter {
    private val movieRepository = MovieRepository()
    private val count = savedCount?.let { Count(it) } ?: Count()

    override fun assignInitialView() {
        movieRepository.findMovieById(movieId)
            .onSuccess { movie ->
                ticketingContractView.assignInitialView(movie, count.value)
            }
            .onFailure {
                ticketingContractView.showErrorMessage(it.message)
            }
    }

    override fun decreaseCount() {
        count.decrease()
        ticketingContractView.updateCount(count.value)
    }

    override fun increaseCount() {
        count.increase()
        ticketingContractView.updateCount(count.value)
    }

    override fun navigate() {
        val totalPrice = Tickets(count).totalPrice
        ticketingContractView.navigate(movieId, count.value, totalPrice)
    }
}
