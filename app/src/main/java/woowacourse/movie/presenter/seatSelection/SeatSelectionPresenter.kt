package woowacourse.movie.presenter.seatSelection

import woowacourse.movie.model.movie.MovieToReserve
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatGridElement
import woowacourse.movie.model.ticket.MovieTicket

class SeatSelectionPresenter(
    private val view: SeatSelectionContracts.View,
) : SeatSelectionContracts.Presenter {
    private lateinit var movieToReserve: MovieToReserve
    private var seats: MutableList<Seat> = mutableListOf()

    override fun loadSeats(
        row: Int,
        column: Int,
    ) {
        val seats: List<Seat> =
            (0 until row).flatMap { row ->
                (0 until column).map { col ->
                    Seat(row, col)
                }
            }
        view.showSeats(seats)
    }

    override fun updateReservationInfo(movieToReserve: MovieToReserve) {
        this.movieToReserve = movieToReserve

        view.showMovieTitle(movieToReserve.title)
        view.showPrice(0)
        view.showButtonEnabled(false)
    }

    override fun updateSelectedSeat(
        row: Int,
        column: Int,
    ) {
        val selectedSeat = Seat(SeatGridElement(row), SeatGridElement(column))
        if (seats.contains(selectedSeat)) {
            seats.remove(selectedSeat)
        } else {
            seats.add(selectedSeat)
        }

        updateButtonEnabled()
        updateTotalPrice()
    }

    private fun updateButtonEnabled() {
        val buttonEnabled = seats.size == movieToReserve.ticketCount.value
        view.showButtonEnabled(buttonEnabled)
    }

    private fun updateTotalPrice() {
        val totalPrice: Int = seats.sumOf { seat -> seat.price }
        view.showPrice(totalPrice)
    }

    override fun createMovieTicket() {
        val movieTicket =
            MovieTicket(
                title = movieToReserve.title,
                movieDate = movieToReserve.movieDate.value,
                movieTime = movieToReserve.movieTime,
                seats = seats.toList(),
            )
        view.showReservationCompleteView(movieTicket)
    }
}
