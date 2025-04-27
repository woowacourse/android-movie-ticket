package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.TicketType

class BookingSeatPresenter(
    val bookingSeatView: BookingSeatContract.View,
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
        val row = seatTag[0] - 'A'
        val col = seatTag[1].digitToInt() - 1
        val seat = Seat(row, col, ticketTypeByRow(row))

        if (seats.contains(seat)) {
            seats.remove(seat)
            bookingSeatView.unselectSeat(row to col)
        } else if (seats.size < headcount.count) {
            seats.add(seat)
            bookingSeatView.selectSeat(row to col)
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

    private fun ticketTypeByRow(row: Int): TicketType =
        when {
            row < 2 -> TicketType.B_GRADE
            row < 4 -> TicketType.S_GRADE
            row < 5 -> TicketType.A_GRADE
            else -> TicketType.B_GRADE
        }
}
