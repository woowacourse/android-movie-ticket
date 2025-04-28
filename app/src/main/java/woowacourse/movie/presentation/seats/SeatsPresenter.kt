package woowacourse.movie.presentation.seats

import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatPosition
import woowacourse.movie.domain.model.seat.SelectedSeats

class SeatsPresenter(
    private val view: SeatsContract.View,
    private val movieTicket: MovieTicket,
) : SeatsContract.Presenter {
    private var selectedSeats = SelectedSeats(movieTicket.headCount)

    override fun onViewCreated() {
        view.initSeats()
        view.showMovieTitle(movieTicket.title)
        view.updateAmount(movieTicket.amount)
    }

    override fun getSeat(
        x: Int,
        y: Int,
    ): Seat {
        val seatPosition = SeatPosition(x, y)
        return Seat(seatPosition)
    }

    override fun getSelectedSeats(): List<Seat> = selectedSeats.value

    override fun isSelectedSeat(seat: Seat): Boolean = selectedSeats.isSelected(seat)

    override fun onSeatClicked(seat: Seat) {
        runCatching {
            selectedSeats.updateSelection(seat)
            view.updateAmount(selectedSeats.getTotalPrice())
            view.updateConfirmButtonEnabled(selectedSeats.isFull())
        }.onFailure {
            view.showToast(it.message ?: it.stackTraceToString())
        }
    }

    override fun onConfirmClicked() {
        val movieTicket =
            MovieTicket(
                title = this.movieTicket.title,
                screeningDateTime = this.movieTicket.screeningDateTime,
                headCount = this.movieTicket.headCount,
                amount = selectedSeats.getTotalPrice(),
                seats = selectedSeats.value,
            )
        view.navigateToSummary(movieTicket)
    }

    override fun onConfigurationChanged(seats: List<Seat>) {
        selectedSeats = SelectedSeats(movieTicket.headCount, seats.toMutableSet())
        view.updateSelectedSeats(selectedSeats.value)
        view.updateAmount(selectedSeats.getTotalPrice())
        view.updateConfirmButtonEnabled(selectedSeats.isFull())
    }
}
