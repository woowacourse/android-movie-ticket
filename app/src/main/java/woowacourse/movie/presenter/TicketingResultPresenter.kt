package woowacourse.movie.presenter

import woowacourse.movie.MOVIES
import woowacourse.movie.model.Tickets
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract,
    private val movieId: Int,
    private val numberOfPeople: Int,
) {
    private val movie = MOVIES.find { it.id == movieId }

    fun assignInitialView() {
        movie?.let {
            ticketingResultView.assignInitialView(
                numberOfPeople,
                movie.title,
                movie.date,
                Tickets(numberOfPeople).totalPrice,
            )
        }
    }
}
