package woowacourse.movie.contract.ticket

import java.time.LocalDateTime

interface TicketContract {
    interface Presenter {
        fun presentTitle()

        fun presentShowtime()

        fun presentCancelDescription()
    }

    interface View {
        fun setMovieTitle(movieTitle: String)

        fun setShowtime(showtime: LocalDateTime)

        fun setCancelDescription(minutes: Int)
    }
}
