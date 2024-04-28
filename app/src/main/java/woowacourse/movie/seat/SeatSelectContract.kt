package woowacourse.movie.seat

import woowacourse.movie.model.Ticket

interface SeatSelectContract {
    interface View {
        fun showReservationInfo(
            movieTitle: String,
            totalPrice: Int,
        )

        fun showTotalPrice(totalPrice: Int)

        fun showReservationCheck(isAvailable: Boolean)

        fun changeSeatColor(
            isSelected: Boolean,
            onColor: (Int) -> Unit,
        )

        fun showConfirmDialog()

        fun moveToReservationFinished(
            movieId: Int,
            ticket: Ticket,
            seats: String,
            totalPrice: Int,
        )
    }

    interface Presenter {
        fun loadSavedData()

        fun loadMovieTitle()

        fun loadReservationInformation()

        fun selectSeat(
            seat: String,
            onColor: (Int) -> Unit,
        )

        fun unselectSeat(
            seat: String,
            onColor: (Int) -> Unit,
        )

        fun confirm()
    }
}
