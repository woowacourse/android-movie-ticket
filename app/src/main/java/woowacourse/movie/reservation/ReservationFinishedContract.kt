package woowacourse.movie.reservation

interface ReservationFinishedContract {
    interface View {
        fun showReservationInformation(
            movieTitle: String,
            screeningDate: String,
            screeningTime: String,
            people: Int,
            seats: String,
            totalPrice: Int,
        )
    }

    interface Presenter {
        fun loadReservationInformation()
    }
}
