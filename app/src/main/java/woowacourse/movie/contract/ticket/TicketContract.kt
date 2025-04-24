package woowacourse.movie.contract.ticket

import java.time.LocalDateTime

interface TicketContract {
    interface Presenter {
        fun presentTitle()

        fun presentShowtime()
    }

    interface View {
        fun setMovieTitle(movieTitle: String)

        fun setShowtime(showtime: LocalDateTime)
    }
}
