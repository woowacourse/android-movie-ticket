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
    val reservationDetail = ReservationDetail(reservationCount)
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
            unSelectingWork(row, col)
        } else {
            selectingWork(row, col)
        }
        view.updateSelectCompletion(reservationDetail.checkSelectCompletion())
        view.showReservationTotalAmount(reservationDetail.totalSeatAmount())
    }

    private fun selectingWork(
        row: Int,
        col: Int,
    ) {
        if (reservationDetail.addSeat(row, col)) view.showSelectedSeat(row, col)
    }

    private fun unSelectingWork(
        row: Int,
        col: Int,
    ) {
        reservationDetail.removeSeat(row, col)
        view.showUnSelectedSeat(row, col)
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
