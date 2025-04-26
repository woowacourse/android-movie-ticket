package woowacourse.movie.presenter.reservationComplete

import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.ticket.MovieTicket
import java.time.LocalDate

interface ReservationCompleteContracts {
    interface View {
        fun showTitle(title: String)

        fun showTimestamp(
            date: LocalDate,
            time: Int,
        )

        fun showSeat(seats: List<Seat>)

        fun showPrice(price: Int)
    }

    interface Presenter {
        fun updateTicketData(movieTicket: MovieTicket)
    }
}
