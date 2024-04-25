package woowacourse.movie.result.presenter.contract

import woowacourse.movie.model.MovieTicket

interface MovieResultContract {
    interface View {
        fun displayMovieTicket(movieTicketData: MovieTicket?)
    }

    interface Presenter {
        fun loadMovieTicket(
            title: String,
            date: String,
            time: String,
            count: Int,
            seats: String,
        )
    }
}
