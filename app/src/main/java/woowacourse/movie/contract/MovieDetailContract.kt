package woowacourse.movie.contract

import woowacourse.movie.model.Reservation
import woowacourse.movie.model.movieInfo.MovieInfo

interface MovieDetailContract {
    interface View {
        fun displayMovie(movie: MovieInfo)

        fun displayTicketNum(ticketNum: Int)

        fun navigateToPurchaseConfirmation(reservation: Reservation)
    }

    interface Presenter {
        fun plusTicketNum()

        fun minusTicketNum()

        fun onBuyButtonClicked()
    }
}
