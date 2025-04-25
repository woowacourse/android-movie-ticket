package woowacourse.movie.view.booking.complete

import woowacourse.movie.domain.model.BookedTicket

interface BookingCompleteContract {
    interface Presenter {
        fun fetchBookedTicket(): BookedTicket

        fun updateBookedTicketInfoViews()

        fun updateBookedTicketPrice()
    }

    interface View {
        fun getBookedTicket(): BookedTicket?

        fun setBookedTicketInfoViews(bookedTicket: BookedTicket)

        fun setBookedTicketPriceTextView(price: Int)
    }
}
