package woowacourse.movie.presenter

import woowacourse.movie.model.MovieData
import woowacourse.movie.model.Result
import woowacourse.movie.presenter.contract.TicketingResultContract

class TicketingResultPresenter(
    private val ticketingResultView: TicketingResultContract.View,
) : TicketingResultContract.Presenter {
    override fun initializeTicketingResult(
        screeningId: Long,
        count: Int,
        totalPrice: Int,
        date: String,
        time: String,
        seats: Array<String>,
    ) {
        when (val screening = MovieData.findScreeningDataById(screeningId)) {
            is Result.Success -> {
                screening.data.movie?.let { movie ->
                    ticketingResultView.assignInitialView(
                        count,
                        movie.title,
                        date,
                        time,
                        totalPrice,
                        seats.toList(),
                    )
                }
            }
            is Result.Error -> {
                ticketingResultView.showToastMessage(screening.message)
            }
        }
    }
}
