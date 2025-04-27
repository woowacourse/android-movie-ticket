package woowacourse.movie.presenter.movieReservationResult

interface MovieReservationResultContract {
    interface View {
        fun showMovieTitle(title: String)

        fun showMovieDateTime(showtime: String)

        fun showTicketCount(count: String)

        fun showSelectedSeats(seats: String)

        fun showTotalPrice(price: String)
    }

    interface Presenter {
        fun onViewCreated()
    }
}
