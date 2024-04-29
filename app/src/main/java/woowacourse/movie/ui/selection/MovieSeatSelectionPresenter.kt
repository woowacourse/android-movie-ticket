package woowacourse.movie.ui.selection

import woowacourse.movie.model.data.UserTickets
import woowacourse.movie.model.movie.Seat
import woowacourse.movie.model.movie.UserTicket

class MovieSeatSelectionPresenter(
    private val view: MovieSeatSelectionContract.View,
    private val userTickets: UserTickets,
) :
    MovieSeatSelectionContract.Presenter {
    private lateinit var userTicket: UserTicket

    override fun loadTheaterInfo(ticketId: Long) {
        try {
            userTicket = userTickets.find(ticketId)
            view.showMovieTitle(userTicket.title)
            view.showReservationTotalAmount(userTicket.reservationDetail.totalSeatAmount())
            view.showTheater(Seat.ROW_LEN, Seat.COL_LEN)
        } catch (e: NoSuchElementException) {
            view.showError(e)
        }
    }

    override fun updateSelectCompletion() {
        view.updateSelectCompletion(userTicket.reservationDetail.checkSelectCompletion())
    }

    override fun selectSeat(
        row: Int,
        col: Int,
    ) {
        if (userTicket.reservationDetail.selectedSeat.contains(Seat(row, col))) {
            unSelectingWork(row, col)
        } else {
            selectingWork(row, col)
        }
        view.updateSelectCompletion(userTicket.reservationDetail.checkSelectCompletion())
        view.showReservationTotalAmount(userTicket.reservationDetail.totalSeatAmount())
    }

    private fun selectingWork(
        row: Int,
        col: Int,
    ) {
        if (userTicket.reservationDetail.addSeat(row, col)) view.showSelectedSeat(row, col)
    }

    private fun unSelectingWork(
        row: Int,
        col: Int,
    ) {
        userTicket.reservationDetail.removeSeat(row, col)
        view.showUnSelectedSeat(row, col)
    }

    override fun reserveMovie(ticketId: Long) {
        view.moveMovieReservationCompletePage(ticketId)
    }
}
