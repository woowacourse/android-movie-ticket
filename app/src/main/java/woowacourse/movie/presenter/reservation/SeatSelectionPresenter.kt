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
    private val ticket = Ticket()
    private val seats = Seats()

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

    override fun updateTotalPrice(
        isSelected: Boolean,
        seat: Seat,
    ) {
        seats.manageSelected(isSelected, seat)
        val totalPrice = ticket.calculatePrice(seats)
        view.showTotalPrice(totalPrice)
    }

    override fun initializeConfirmButton() {
        view.launchReservationConfirmDialog(seats)
    }
}
