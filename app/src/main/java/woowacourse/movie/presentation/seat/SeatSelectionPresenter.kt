package woowacourse.movie.presentation.seat

import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.MovieSeats
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.repository.SeatRepository
import woowacourse.movie.presentation.model.TicketModel
import woowacourse.movie.presentation.model.toTicketModel
import woowacourse.movie.presentation.seat.model.SeatSelectType

class SeatSelectionPresenter(
    private val ticketModel: TicketModel,
    private val view: SeatSelectionContract.View,
    private val seatRepository: SeatRepository,
) : SeatSelectionContract.Presenter {
    private val movieSeats = MovieSeats(ticketModel.count)

    override fun loadTicket() {
        view.showTicket(ticketModel = ticketModel)
    }

    override fun loadSeat() {
        view.showSeat(seatRepository.getSeats())
    }

    override fun selectSeat(
        rowIndex: Int,
        columIndex: Int,
    ) {
        val seat = seatRepository.getSeat(rowIndex, columIndex)
        if (seat.seatName.isEmpty()) return
        movieSeats.setSeatSelectType(seat)
        when (movieSeats.seatSelectType) {
            SeatSelectType.ADD -> {
                movieSeats.addSeat(seat)
                view.showSelectedSeat(rowIndex, columIndex)
                view.showCurrentResultTicketPriceView(movieSeats.getSeatPrice())
            }

            SeatSelectType.REMOVE -> {
                movieSeats.deleteSeat(seat)
                view.showUnSelectedSeat(rowIndex, columIndex)
                view.showCurrentResultTicketPriceView(movieSeats.getSeatPrice())
            }

            SeatSelectType.PREVENT -> {}
        }
        updateSeatTypeInView()
    }

    override fun getSeats(): List<MovieSeat> {
        return movieSeats.userSeats
    }

    override fun ticketing() {
        val ticket = Ticket(
            title = ticketModel.title,
            movieDate = MovieDate(
                ticketModel.screeningDate,
                ticketModel.screeningTime,
            ),
            count = ticketModel.count,
            price = movieSeats.getSeatPrice(),
            seats = movieSeats.userSeats,
        ).toTicketModel()
        view.moveToTicketDetail(ticket)
    }

    override fun confirmSeatResult() {
        if (movieSeats.seatSelectType == SeatSelectType.PREVENT) {
            view.showDialog()
        }
    }

    private fun updateSeatTypeInView(){
        movieSeats.updateSeatSelectType()
        when(movieSeats.seatSelectType){
            SeatSelectType.ADD,SeatSelectType.REMOVE ->  view.offConfirmAvailableView()
            SeatSelectType.PREVENT -> view.onConfirmAvailableView()
        }
    }
}
