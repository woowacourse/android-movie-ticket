package woowacourse.movie.ui.seat.presenter

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.ui.seat.contract.BookingSeatContract
import woowacourse.movie.ui.seat.model.SeatState

class BookingSeatPresenter(
    private val bookingSeatView: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private var _headcount: Headcount = Headcount()
    val headcount get() = _headcount.deepCopy()
    private var movieTitle: String = ""

    private var seats: Seats = Seats()

    fun updateViews() {
        refreshMovieTitle()
        refreshTotalPrice()
        refreshConfirmButton()
    }

    override fun restoreState(seatState: SeatState) {
        with(seatState) {
            this@BookingSeatPresenter._headcount = headcount
            this@BookingSeatPresenter.movieTitle = movieTitle
            this@BookingSeatPresenter.seats = selectedSeats
        }
    }

    override fun refreshTotalPrice() {
        bookingSeatView.setTotalPrice(seats.totalPrice())
    }

    override fun refreshMovieTitle() {
        bookingSeatView.setMovieTitle(movieTitle)
    }

    override fun selectSeat(seat: Seat) {
        if (seats.contains(seat)) {
            seats.remove(seat)
            bookingSeatView.toggleSeat(seat, false)
        } else if (seats.size < headcount.count) {
            seats.add(seat)
            bookingSeatView.toggleSeat(seat, true)
        }
        refreshTotalPrice()
        refreshConfirmButton()
    }

    override fun refreshConfirmButton() {
        if (seats.size != headcount.count) {
            bookingSeatView.setConfirmButton(false)
        } else {
            bookingSeatView.setConfirmButton(true)
        }
    }

    override fun completeBookingSeat() {
        bookingSeatView.startBookingCompleteActivity(movieTitle, headcount, seats)
    }
}
