package woowacourse.movie.seat

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

        fun moveToReservationFinished()
    }

    interface Presenter {
        fun loadMovieTitle()

        fun selectSeat(
            position: Int,
            onColor: (Int) -> Unit,
        )

        fun unselectSeat(
            position: Int,
            onColor: (Int) -> Unit,
        )

        fun confirm()
    }
}
