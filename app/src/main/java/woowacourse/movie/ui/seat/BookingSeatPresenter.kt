package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats

class BookingSeatPresenter(
    private val bookingSeatView: BookingSeatContract.View,
) : BookingSeatContract.Presenter {
    private val headcount: Headcount by lazy { fetchHeadcount() }
    private val movieTitle: String by lazy { fetchMovieTitle() }
    private var seats: Seats = Seats()

    fun fetchData() {
        fetchHeadcount()
        fetchMovieTitle()
    }

    fun updateViews() {
        updateMovieTitle()
        updateTotalPrice()
        updateConfirmButton()
    }

    override fun fetchHeadcount(): Headcount = bookingSeatView.getHeadcount() ?: Headcount()

    override fun fetchMovieTitle(): String = bookingSeatView.getMovieTitle() ?: "EMPTY"

    override fun updateTotalPrice() {
        bookingSeatView.setTotalPriceTextView(seats.totalPrice())
    }

    override fun updateMovieTitle() {
        bookingSeatView.setMovieTitleTextView(movieTitle)
    }

    override fun updateSeat(seatTag: String) {
        val seat = Seat.fromSeatTag(seatTag)

        if (seats.contains(seat)) {
            seats.remove(seat)
            bookingSeatView.toggleSeat(seat, false)
        } else if (seats.size < headcount.count) {
            seats.add(seat)
            bookingSeatView.toggleSeat(seat, true)
        }
        updateTotalPrice()
        updateConfirmButton()
    }

    override fun updateConfirmButton() {
        if (seats.size != headcount.count) {
            bookingSeatView.setConfirmButton(false)
        } else {
            bookingSeatView.setConfirmButton(true)
        }
    }

    override fun completeBookingSeat() {
        bookingSeatView.moveToBookingCompleteActivity(movieTitle, headcount, seats)
    }
}
