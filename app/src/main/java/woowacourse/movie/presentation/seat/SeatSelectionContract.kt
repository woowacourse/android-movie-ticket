package woowacourse.movie.presentation.seat

import android.os.Bundle
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.model.MovieSeatModel

interface SeatSelectionContract {
    interface View {
        fun showTicket(pendingMovieReservationModel: PendingMovieReservationModel)

        fun showSeat(seats: List<List<MovieSeat>>)

        fun showSelectedSeat(
            rowIndex: Int,
            columnIndex: Int,
        )

        fun showUnSelectedSeat(
            rowIndex: Int,
            columnIndex: Int,
        )

        fun showCurrentResultTicketPriceView(seatPrice: Int)

        fun offConfirmAvailableView()

        fun onConfirmAvailableView()

        fun moveToTicketDetail(ticket: TicketModel)

        fun showDialog()
    }

    interface Presenter {
        fun loadTicket()

        fun loadSeat()

        fun selectSeat(
            rowIndex: Int,
            columIndex: Int,
        )

        fun getSeats(): List<MovieSeat>

        fun ticketing()

        fun confirmSeatResult()

        fun saveInstance(outState: Bundle)

        fun initSavedInstance(seats: List<MovieSeatModel>)
    }
}
