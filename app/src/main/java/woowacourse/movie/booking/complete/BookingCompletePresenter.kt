package woowacourse.movie.booking.complete

import woowacourse.movie.booking.detail.TicketUiModel

class BookingCompletePresenter(
    private val view: BookingCompleteContract.View,
    private val ticket: TicketUiModel,
) : BookingCompleteContract.Presenter {
    override fun initializeData() {
        view.showBookingCompleteResult(ticket)
    }
}
