package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

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

    override fun refreshTotalPrice() {
        bookingSeatView.setTotalPrice(seats.totalPrice())
    }

    override fun refreshMovieTitle() {
        bookingSeatView.setMovieTitle(movieTitle)
    }

    override fun restoreHeadcount(headcount: Headcount) {
        _headcount = headcount
    }

    override fun restoreMovieTitle(movieTitle: String) {
        this.movieTitle = movieTitle
    }

    override fun selectSeat(seatTag: String) {
        val seat = Seat.fromSeatTag(seatTag)

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
