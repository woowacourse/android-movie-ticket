package woowacourse.movie.presentation.ui.seat

import woowacourse.movie.domain.repository.MovieTicketRepository
import woowacourse.movie.domain.repository.SeatRepository

class SeatPresenter(
    private val view: SeatContract.View,
    private val movieTicketRepository: MovieTicketRepository,
    private val seatRepository: SeatRepository,
    private val movieTicketId: Int,
) : SeatContract.Presenter {
    private val seats = seatRepository.getAllSeats().seats
    private var reservationCount: Int = 0
    
    init {
        if (movieTicketId <= INVALID_MOVIE_TICKET_ID) {
            view.showMessage(ERROR_MESSAGE.format(INVALID_MOVIE_TICKET_ID_MESSAGE))
        } else {
            loadScreeningInformation()
        }
    }
    
    override fun loadScreeningInformation() {
        runCatching {
            movieTicketRepository.getMovieTicket(movieTicketId)
        }.onSuccess { movieTicket ->
            view.showMovieTitle(movieTicket.movieTitle)
            view.showSeats(seats)
            reservationCount = movieTicket.reservationCount
        }.onFailure {
            view.showMessage(ERROR_MESSAGE.format(it.message ?: ""))
        }
    }
    

    companion object {
        const val INVALID_MOVIE_TICKET_ID = -1
        const val INVALID_MOVIE_TICKET_ID_MESSAGE = "올바르지 않은 예매 ID입니다."
        const val ERROR_MESSAGE = "예매 정보를 불러오는데 실패했습니다. %s"
    }
}
