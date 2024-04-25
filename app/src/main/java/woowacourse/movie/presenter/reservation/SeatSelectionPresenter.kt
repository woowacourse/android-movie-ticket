package woowacourse.movie.presenter.reservation

import woowacourse.movie.db.ScreeningDao
import woowacourse.movie.db.SeatsDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket

class SeatSelectionPresenter(
    private val view: SeatSelectionContract.View,
    private val seatsDao: SeatsDao,
    private val screeningDao: ScreeningDao,
) : SeatSelectionContract.Presenter {
    val ticket = Ticket()
    val seats = Seats()

    override fun restoreReservation(count: Int) {
        ticket.restoreTicket(count)
        view.setConfirmButtonEnabled(ticket.count)
        view.showTotalPrice(ticket.calculatePrice(seats))
    }

    override fun restoreSeats(
        selectedSeats: Seats,
        seatsIndex: List<Int>,
    ) {
        seats.restoreSeats(selectedSeats)
        seats.restoreSeatsIndex(seatsIndex)
        view.restoreSelectedSeats(seatsIndex)
    }

    override fun loadSeatNumber() {
        val seats = seatsDao.findAll()
        seats.forEachIndexed { index, seat ->
            view.showSeatNumber(index, seat)
        }
    }

    override fun loadMovie(movieId: Int) {
        val movie: Movie = screeningDao.find(movieId)
        view.showMovieTitle(movie)
    }

    override fun manageSelectedSeats(
        isSelected: Boolean,
        index: Int,
        seat: Seat,
    ) {
        seats.apply {
            manageSelectedIndex(isSelected, index)
            manageSelected(isSelected, seat)
        }
    }

    override fun updateTotalPrice(
        isSelected: Boolean,
        seat: Seat,
    ) {
        val totalPrice = ticket.calculatePrice(seats)
        view.showTotalPrice(totalPrice)
    }

    override fun initializeConfirmButton() {
        view.launchReservationConfirmDialog(seats)
    }
}
