package woowacourse.movie.presenter

import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.SeatToggleResult
import woowacourse.movie.model.ticket.seat.Seats
import woowacourse.movie.model.ticket.seat.grade.RowBasedSeatGradePolicy
import woowacourse.movie.view.model.ScreeningData
import woowacourse.movie.view.model.TicketData
import woowacourse.movie.view.selectSeat.SelectSeatView

class SelectSeatPresenter(
    private val view: SelectSeatView,
) {
    private val screeningData: ScreeningData by lazy { ticketDataEmptySeat.screeningData }
    private val screening: Screening by lazy { screeningData.toScreening() }
    private val ticketDataEmptySeat: TicketData by lazy {
        view.getTicketData()
    }
    private val selectedSeats: Seats by lazy {
        Seats(seatGradePolicy = RowBasedSeatGradePolicy())
    }

    fun initSelectSeatUI() {
        view.initMovieTitleUI(ticketDataEmptySeat)
        view.setTicketPrice(selectedSeats.totalTicketPrice)
    }

    fun toggleSeat(seat: Seat) {
        val toggleResult = selectedSeats.toggleSeat(seat)
        when (toggleResult) {
            is SeatToggleResult.Added -> view.seatSelect(seat)
            is SeatToggleResult.Removed -> view.seatUnSelect(seat)
        }
        view.setTicketPrice(selectedSeats.totalTicketPrice)
    }

    fun navigateToTicketUI() {
        view.navigateToTicketUI(ticketDataEmptySeat) // TODO: 좌석정보 추가 필요
    }
}
