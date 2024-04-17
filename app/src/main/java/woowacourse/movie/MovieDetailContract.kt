package woowacourse.movie

interface MovieDetailContract {
    interface View {
        fun onCountUpdate(count: Int)

        fun onReservationComplete(movieTicket: MovieTicket)
    }

    interface Presenter {
        fun plusReservationCount()

        fun minusReservationCount()

        fun reservation(movie: Movie)
    }
}
