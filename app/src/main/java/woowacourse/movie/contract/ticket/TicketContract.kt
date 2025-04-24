package woowacourse.movie.contract.ticket

interface TicketContract {
    interface Presenter {
        fun presentTitle()
    }

    interface View {
        fun setMovieTitle(movieTitle: String)
    }
}
