package woowacourse.movie.presentation.seats

import woowacourse.movie.domain.model.movie.MovieTicket
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatPosition
import woowacourse.movie.domain.model.seat.SelectedSeats
import java.util.Locale

class SeatsPresenter(
    private val view: SeatsContract.View,
) : SeatsContract.Presenter {
    private lateinit var movieTicket: MovieTicket
    lateinit var selectedSeats: SelectedSeats
        private set

    override fun loadSeats(movieTicket: MovieTicket) {
        this.movieTicket = movieTicket.copy()
        selectedSeats = SelectedSeats(movieTicket.headCount)
        view.initSeats()
        view.showMovieTitle(movieTicket.title)
        view.updateAmount(movieTicket.amount)
    }

    override fun getSelectedSeats(): List<Seat> = selectedSeats.value

    override fun selectSeat(seatPosition: SeatPosition) {
        runCatching {
            val seat = Seat(seatPosition)
            selectedSeats.updateSelection(seat)
            view.updateSelectedSeat(seatPosition, selectedSeats.isSelected(seat))
            view.updateAmount(selectedSeats.getTotalPrice())
            view.updateConfirmButtonEnabled(selectedSeats.isFull())
        }.onFailure { e ->
            val message = e.message?.let {
                String.format(Locale.getDefault(), it, movieTicket.headCount)
            } ?: e.stackTraceToString()
            view.showToast(message)
        }
    }

    override fun publishMovieTicket() {
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

    override fun restoreSeats(seats: List<Seat>) {
        selectedSeats = SelectedSeats(movieTicket.headCount, seats.toMutableSet())
        selectedSeats.value.forEach { seat -> view.updateSelectedSeat(seat.seatPosition, true) }
        view.updateAmount(selectedSeats.getTotalPrice())
        view.updateConfirmButtonEnabled(selectedSeats.isFull())
    }
}
