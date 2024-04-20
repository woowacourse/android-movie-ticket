package woowacourse.movie.presenter

import woowacourse.movie.model.MovieTicket

interface MovieResultContract {
    interface View {
        fun displayMovieTicket(movieTicketData: MovieTicket?)
    }

    interface Presenter {
        fun loadMovieTicket(
            id: Long,
            count: Int,
        )
    }
}
