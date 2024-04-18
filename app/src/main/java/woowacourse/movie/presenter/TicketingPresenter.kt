package woowacourse.movie.presenter

import woowacourse.movie.MOVIES
import woowacourse.movie.model.Count
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingPresenter(
    private val ticketingContractView: TicketingContract,
    private val movieId: Int,
) {
    private val movie = MOVIES.find { it.id == movieId }
    private var count = Count()

    fun assignInitialView() {
        movie?.let { ticketingContractView.assignInitialView(movie, count.value) }
    }

    fun decreaseCount() {
        count.decrease()
        ticketingContractView.updateCount(count.value)
    }

    fun increaseCount() {
        count.increase()
        ticketingContractView.updateCount(count.value)
    }

    fun navigate() {
        movie?.let { ticketingContractView.navigate(it.id, count.value) }
    }
}
