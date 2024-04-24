package woowacourse.movie.presentation.ui.reservation

import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.presentation.uimodel.MovieTicketUiModel

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val movieTicketRepository: MovieTicketRepository,
    private val movieTicketId: Int,
) : ReservationResultContract.Presenter {
    init {
        if (movieTicketId <= INVALID_MOVIE_TICKET_ID) {
            view.showMessage(ERROR_MESSAGE)
        } else {
            loadReservationDetails()
        }
    }

    override fun loadReservationDetails() {
        runCatching {
            movieTicketRepository.getMovieTicket(movieTicketId)
        }.onSuccess { ticket ->
            val movieTicketUiModel = MovieTicketUiModel.fromMovieTicket(ticket)
            view.showTicketData(movieTicketUiModel)
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message))
        }
    }

    companion object {
        const val INVALID_MOVIE_TICKET_ID = -1
        const val ERROR_MESSAGE = "예매 정보를 불러오는데 실패했습니다. %s"
    }
}
