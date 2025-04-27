package woowacourse.movie.view.reservation.seat

interface ReservationSeatContract {
    interface Present {
        fun fetchData()
    }

    interface View {
        fun showReservationSeatScreen()
    }
}
