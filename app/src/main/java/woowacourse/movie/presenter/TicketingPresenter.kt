package woowacourse.movie.presenter

import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Count
import woowacourse.movie.model.Result
import woowacourse.movie.model.Tickets
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
    movieId: Int,
    savedCount: Int?,
) : TicketingContract.Presenter {
    private val movie = MovieRepository().findMovieById(movieId)
    private val count = savedCount?.let { Count(it) } ?: Count()

    override fun assignInitialView() {
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
