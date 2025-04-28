package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

class BookingSeatPresenter(
    private val bookingSeatView: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private val headcount: Headcount by lazy { loadHeadcount() }
    private val movieTitle: String by lazy { loadMovieTitle() }
    private var seats: Seats = Seats()

    fun fetchData() {
        loadHeadcount()
        loadMovieTitle()
    }

    fun updateViews() {
        refreshMovieTitle()
        refreshTotalPrice()
        refreshConfirmButton()
    }

    override fun loadHeadcount(): Headcount = bookingSeatView.getHeadcount() ?: Headcount()

    override fun loadMovieTitle(): String = bookingSeatView.getMovieTitle() ?: "EMPTY"

    override fun refreshTotalPrice() {
        bookingSeatView.setTotalPrice(seats.totalPrice())
    }

    override fun refreshMovieTitle() {
        bookingSeatView.setMovieTitle(movieTitle)
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
