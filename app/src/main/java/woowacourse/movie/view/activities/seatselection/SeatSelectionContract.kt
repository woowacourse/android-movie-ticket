package woowacourse.movie.view.activities.seatselection

interface SeatSelectionContract {
    interface Presenter {
        fun loadScreening()

        fun setSelectedSeats(seatNames: Set<String>)

        fun reserve(seatNames: Set<String>)
    }

    interface View {
        fun setSeats(seatUIStates: List<List<SeatUIState>>)

        fun setMovieTitle(title: String)

        fun setReservationFee(fee: Int)

        fun startReservationResultActivity(reservationId: Long)
    }
}
