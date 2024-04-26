package woowacourse.movie.ui.selection

import woowacourse.movie.model.data.MovieContents
import woowacourse.movie.model.data.UserTickets
import woowacourse.movie.model.movie.MovieContent
import woowacourse.movie.model.movie.ReservationDetail
import woowacourse.movie.model.movie.Seat
import woowacourse.movie.model.movie.UserTicket

class MovieSeatSelectionPresenter(
    private val view: MovieSeatSelectionContract.View,
    private val movieContents: MovieContents,
    private val userTickets: UserTickets,
    reservationCount: Int,
) :
    MovieSeatSelectionContract.Presenter {
    private val reservationDetail = ReservationDetail(reservationCount)
    private lateinit var movieContent: MovieContent

    override fun loadMovieTitle(movieContentId: Long) {
        try {
            movieContent = movieContents.find(movieContentId)
            view.showMovieTitle(movieContent.title)
        } catch (e: NoSuchElementException) {
            view.showError(e)
        }
    }

    override fun loadTotalSeatAmount() {
        view.showReservationTotalAmount(reservationDetail.totalSeatAmount())
    }

    override fun loadTheater() {
        view.showTheater(Seat.ROW_LEN, Seat.COL_LEN)
    }

    override fun updateSelectCompletion() {
        view.updateSelectCompletion(reservationDetail.checkSelectCompletion())
    }

    override fun selectSeat(
        row: Int,
        col: Int,
    ) {
        if (reservationDetail.selectedSeat.contains(Seat(row, col))) {
            reservationDetail.removeSeat(row, col)
        } else {
            reservationDetail.addSeat(row, col)
        }
        view.showSelectedSeat(reservationDetail.selectedSeat)
    }

    override fun reserveMovie(
        date: String,
        time: String,
    ) {
        val userTicket = UserTicket(movieContent.title, date, time, reservationDetail)
        val ticketId = userTickets.save(userTicket)
        view.moveMovieReservationCompletePage(ticketId)
    }
}
