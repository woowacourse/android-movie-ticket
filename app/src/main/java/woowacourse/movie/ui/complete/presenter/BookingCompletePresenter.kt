package woowacourse.movie.ui.complete.presenter

import woowacourse.movie.domain.model.BookedTicket
import woowacourse.movie.ui.complete.contract.BookingCompleteContract

class BookingCompletePresenter(
    private val bookingCompleteView: BookingCompleteContract.View,
) : BookingCompleteContract.Presenter {
    private lateinit var bookedTicket: BookedTicket

    fun updateViews() {
        refreshBookedTicketDisplay()
        refreshTicketPrice()
    }

    override fun loadBookedTicket(bookedTicket: BookedTicket) {
        this.bookedTicket = bookedTicket
    }

    override fun refreshTicketPrice() {
        val price = bookedTicket.totalPrice()
        bookingCompleteView.setBookedTicketPrice(price)
    }

    override fun refreshBookedTicketDisplay() {
        bookingCompleteView.setBookedTicket(bookedTicket)
    }
}
