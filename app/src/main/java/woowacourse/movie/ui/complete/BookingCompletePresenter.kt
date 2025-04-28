package woowacourse.movie.ui.complete

import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seats
import java.time.LocalDateTime

class BookingCompletePresenter(
    private val bookingCompleteView: BookingCompleteContract.View,
) : BookingCompleteContract.Presenter {
    private val bookedTicket: BookedTicket by lazy { fetchBookedTicket() }

    fun updateViews() {
        updateBookedTicketInfoViews()
        updateBookedTicketPrice()
    }

    override fun fetchBookedTicket(): BookedTicket =
        bookingCompleteView.getBookedTicket() ?: BookedTicket(
            "NULL",
            Headcount(),
            LocalDateTime.now(),
            Seats(),
        )

    override fun updateBookedTicketPrice() {
        val price = bookedTicket.totalPrice()
        bookingCompleteView.setBookedTicketPriceTextView(price)
    }

    override fun updateBookedTicketInfoViews() {
        bookingCompleteView.setBookedTicketInfoViews(bookedTicket)
    }
}
