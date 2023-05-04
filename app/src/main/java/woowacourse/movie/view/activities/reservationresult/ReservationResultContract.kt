package woowacourse.movie.view.activities.reservationresult

interface ReservationResultContract {
    interface Presenter {
        fun loadReservation()
    }

    interface View {
        fun setReservation(reservationUIState: ReservationUIState)
    }
}
