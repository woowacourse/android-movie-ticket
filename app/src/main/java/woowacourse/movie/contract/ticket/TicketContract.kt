package woowacourse.movie.contract.ticket

import java.time.LocalDateTime

interface TicketContract {
    interface Presenter {
        fun presentTitle()

        fun presentShowtime()

        fun presentCancelDescription()

        fun presentCount()
    }

    interface View {
        fun setMovieTitle(movieTitle: String)

        fun setShowtime(showtime: LocalDateTime)

        fun setCancelDescription(minutes: Int)

        fun setCount(count: Int)
    }
}
