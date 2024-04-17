package woowacourse.movie.presenter

import woowacourse.movie.MOVIES
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingPresenter(
    private val ticketingContractView: TicketingContract,
    private val movieId: Int,
) {
    private val movie = MOVIES.find { it.id == movieId }
    private var count = 1

    fun assignInitialView() {
        movie?.let { ticketingContractView.assignInitialView(movie, count) }
    }

    fun decreaseCount() {
        if (count > 1) {
            count--
            ticketingContractView.updateCount(count)
        }
    }

    fun increaseCount() {
        count++
        ticketingContractView.updateCount(count)
    }

    fun navigate() {
        movie?.let { ticketingContractView.navigate(it.id, count) }
    }
}
