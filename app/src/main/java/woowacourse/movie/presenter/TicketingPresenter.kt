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
    private lateinit var date: String
    private lateinit var time: String

    val countValue: Int
        get() = count.value

    override fun initializeTicketingData() {
        when (movie) {
            is Result.Success -> {
                date = movie.data.startDate.toString()
                time = movie.data.times.first()
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
        when (movie) {
            is Result.Success -> {
                val totalPrice = Tickets(count, movie.data).totalPrice
                ticketingContractView.navigateToTicketingResult(
                    movieId = movie.data.id,
                    count = count.value,
                    totalPrice = totalPrice,
                    date = date,
                    time = time,
                )
            }

            is Result.Error -> {
                ticketingContractView.showToastMessage(movie.message)
            }
        }
    }

    override fun updateDate(date: String) {
        this.date = date
    }

    override fun updateTime(time: String) {
        this.time = time
    }
}
