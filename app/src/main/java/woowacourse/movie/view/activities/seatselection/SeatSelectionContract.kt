package woowacourse.movie.view.activities.seatselection

interface SeatSelectionContract {
    interface Presenter {
        fun loadScreening(screeningId: Long)
    }

    interface View {
        fun setSeats(seatUIStates: List<List<SeatUIState>>)

        fun setMovieTitle(title: String)

        fun setReservationFee(fee: Int)
    }
}
