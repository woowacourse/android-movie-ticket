package woowacourse.movie.presenter

import woowacourse.movie.Result
import woowacourse.movie.findMovieById
import woowacourse.movie.model.Count
import woowacourse.movie.model.Tickets
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingPresenter(
    private val ticketingContractView: TicketingContract,
    movieId: Int,
) {
    private val movie = findMovieById(movieId)
    private var count = Count()

    fun assignInitialView() {
        when (movie) {
            is Result.Success -> {
                ticketingContractView.assignInitialView(
                    movie.data,
                    count.value,
                )
            }
            is Result.Error -> {
                ticketingContractView.showErrorMessage(movie.message)
            }
        }
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
        val totalPrice = Tickets(count).totalPrice
        when (movie) {
            is Result.Success -> {
                ticketingContractView.navigate(movie.data.id, count.value, totalPrice)
            }
            is Result.Error -> {
                ticketingContractView.showErrorMessage(movie.message)
            }
        }
    }
}
