package woowacourse.movie.presenter.reservationComplete

import woowacourse.movie.model.MovieTicket
import java.time.LocalDate

interface ReservationCompleteContracts {
    interface View {
        fun showTitle(title: String)

        fun showTimestamp(
            date: LocalDate,
            time: Int,
        )

        fun showTicketCount(count: Int)

        fun showPrice(price: Int)
    }

    interface Presenter {
        fun updateTicketData(movieTicket: MovieTicket)
    }
}
