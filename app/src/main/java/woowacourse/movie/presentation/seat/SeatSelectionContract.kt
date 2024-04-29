package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.presentation.model.MovieSeatModel
import woowacourse.movie.presentation.model.PendingMovieReservationModel
import woowacourse.movie.presentation.model.TicketModel

interface SeatSelectionContract {
    interface View {
        fun showTicket(pendingMovieReservationModel: PendingMovieReservationModel)

        fun showSeat(seats: List<List<MovieSeat>>)

        fun showSelectedSeat(seat: MovieSeat)

        fun showUnSelectedSeat(seat: MovieSeat)

        fun showCurrentResultTicketPriceView(seatPrice: Int)

        fun offConfirmAvailable()

        fun onConfirmAvailable()

        fun moveToTicketDetail(ticket: TicketModel)

        fun showReservationConfirmationDialog()

    }

    interface Presenter {
        fun loadData()

        fun selectSeat(
            rowIndex: Int,
            columIndex: Int,
        )

        fun getSeats(): List<MovieSeat>

        fun ticketing()

        fun confirmSeatResult()

        fun initSavedInstanceData(seats: List<MovieSeatModel>)

        fun makeSavedSeats() : List<MovieSeatModel>
    }
}
