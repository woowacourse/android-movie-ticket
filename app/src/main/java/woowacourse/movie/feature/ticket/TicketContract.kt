package woowacourse.movie.feature.ticket

import java.time.LocalDateTime

interface TicketContract {
    interface View {
        fun setCancelableMinute(cancelableMinute: Int)

        fun setMovieTitle(movieTitle: String)

        fun setShowTime(showtime: LocalDateTime)

        fun setSeatCodes(seatCodes: List<String>)

        fun setTicketCount(ticketCount: Int)

        fun setTicketPrice(totalTicketPrice: Int)

        fun printError(message: String)
    }

    interface Presenter {
        fun initTicketView()
    }
}
