package woowacourse.movie.presentation.ui.seat

import woowacourse.movie.domain.repository.MovieTicketRepository

class SeatPresenter(
    private val view: SeatContract.View,
    private val movieTicketRepository: MovieTicketRepository,
    private val movieTicketId: Int,
) : SeatContract.Presenter {
    init {
        if (movieTicketId <= INVALID_MOVIE_TICKET_ID) {
            view.showMessage(ERROR_MESSAGE)
        } else {
        
        }
    }

    companion object {
        const val INVALID_MOVIE_TICKET_ID = -1
        const val ERROR_MESSAGE = "예매 정보를 불러오는데 실패했습니다. %s"
    }
}
