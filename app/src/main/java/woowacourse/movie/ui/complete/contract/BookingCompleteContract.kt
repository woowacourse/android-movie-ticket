package woowacourse.movie.ui.complete.contract

import woowacourse.movie.domain.model.BookedTicket

interface BookingCompleteContract {
    interface Presenter {
        fun loadBookedTicket(bookedTicket: BookedTicket)

        fun refreshBookedTicketDisplay()

        fun refreshTicketPrice()
    }

    interface View {
        fun setBookedTicket(bookedTicket: BookedTicket)

        fun setBookedTicketPrice(price: Int)
    }
}
