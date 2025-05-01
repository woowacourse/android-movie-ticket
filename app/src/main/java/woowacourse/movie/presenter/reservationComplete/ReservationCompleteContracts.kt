package woowacourse.movie.presenter.reservationComplete

import woowacourse.movie.model.ticket.MovieTicket

interface ReservationCompleteContracts {
    interface View {
        fun showTicket(movieTicket: MovieTicket)
    }

    interface Presenter {
        fun updateTicketData(movieTicket: MovieTicket)
    }
}
