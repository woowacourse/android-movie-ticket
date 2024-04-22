package woowacourse.movie.presenter

import woowacourse.movie.model.Count
import woowacourse.movie.model.MovieData.findMovieById
import woowacourse.movie.model.Result
import woowacourse.movie.model.Tickets
import woowacourse.movie.presenter.contract.TicketingContract

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
    movieId: Long,
    initialCount: Int,
) : TicketingContract.Presenter {
    private val movie = findMovieById(movieId)
    private val count = Count(initialCount)

    val countValue: Int
        get() = count.value

    override fun initializeTicketingData() {
        when (movie) {
            is Result.Success -> {
                ticketingContractView.assignInitialView(
                    movie.data,
                    count.value,
                )
            }

            is Result.Error -> {
                ticketingContractView.showToastMessage(movie.message)
            }
        }
    }

    override fun decreaseCount() {
        runCatching {
            count.decrease()
            ticketingContractView.updateCount(count.value)
        }.onFailure {
            it.message?.let { message ->
                ticketingContractView.showToastMessage(message)
            }
        }
    }

    override fun increaseCount() {
        count.increase()
        ticketingContractView.updateCount(count.value)
    }

    override fun reserveTickets() {
        val totalPrice = Tickets(count).totalPrice
        when (movie) {
            is Result.Success -> {
                ticketingContractView.navigateToTicketingResult(movie.data.id, count.value, totalPrice)
            }

            is Result.Error -> {
                ticketingContractView.showToastMessage(movie.message)
            }
        }
    }
}
