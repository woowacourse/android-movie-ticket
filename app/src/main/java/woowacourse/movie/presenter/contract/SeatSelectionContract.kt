package woowacourse.movie.presenter.contract

import woowacourse.movie.model.theater.SeatClass
import woowacourse.movie.model.theater.TheaterSize
import woowacourse.movie.model.ticketing.BookingSeat

interface SeatSelectionContract {
    interface View {
        fun initializeSeatTable(
            theaterSize: TheaterSize,
            rowClassInfo: Map<Int, SeatClass>,
            movieTitle: String,
            totalPrice: Int,
            selectedSeats: List<BookingSeat>,
        )

        fun toggleSeat(
            row: Int,
            column: Int,
            seatClass: SeatClass,
            isSelected: Boolean,
            columnSize: Int,
        )

        fun updateTotalPrice(totalPrice: Int)

        fun updateButtonStatus(isAvailable: Boolean)

        fun navigateToResultScreen(
            movieId: Long,
            count: Int,
            seats: List<BookingSeat>,
            totalPrice: Int,
        )

        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun loadSeats(
            screeningId: Long,
            numOfTickets: Int,
            date: String?,
            time: String?,
            title: String?,
            seats: List<BookingSeat>,
        )

        fun makeReservation(
            movieId: Long,
            count: Int,
        )

        fun updateSeat(
            row: Int,
            column: Int,
            seatClass: SeatClass,
            columnSize: Int,
        )
    }
}
