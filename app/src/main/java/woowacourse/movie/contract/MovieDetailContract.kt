package woowacourse.movie.contract

import woowacourse.movie.model.movie.Movie


interface MovieDetailContract {
    interface View {
        fun displayMovie(movie: Movie)

        fun displayScreeningDays()

        fun displayTicketNum(ticketNum: Int)

        fun navigateToPurchaseConfirmation()

    }

    interface Presenter {
        fun loadMovie(movieId: Int)

        fun loadScreeningDays()

        fun plusTicketNum()

        fun minusTicketNum()

        fun purchase(
            screeningId: Int,
        )
    }
}
