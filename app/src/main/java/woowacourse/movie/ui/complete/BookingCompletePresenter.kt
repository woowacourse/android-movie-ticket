package woowacourse.movie.ui.complete

import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.domain.model.Headcount
import woowacourse.movie.domain.model.Seats
import java.time.LocalDateTime

class BookingCompletePresenter(
    private val bookingCompleteView: BookingCompleteContract.View,
) : BookingCompleteContract.Presenter {
    private val bookedTicket: BookedTicket by lazy { loadBookedTicket() }

    fun updateViews() {
        refreshBookedTicketDisplay()
        refreshTicketPrice()
    }

    override fun loadBookedTicket(): BookedTicket =
        bookingCompleteView.getBookedTicket() ?: BookedTicket(
            "NULL",
            Headcount(),
            LocalDateTime.now(),
            Seats(),
        )

    override fun refreshTicketPrice() {
        val price = bookedTicket.totalPrice()
        bookingCompleteView.setBookedTicketPrice(price)
    }

    override fun refreshBookedTicketDisplay() {
        bookingCompleteView.setBookedTicket(bookedTicket)
    }
}
