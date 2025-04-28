package woowacourse.movie.booking.complete

class BookingCompletePretender(
    private val view: BookingCompleteActivity,
) : BookingCompleteContract.Presenter {
    private lateinit var ticketInfo: BookingCompleteUiModel

    override fun loadTicketInfo(uiModel: BookingCompleteUiModel) {
        this.ticketInfo = uiModel
        view.showTicketInfo(ticketInfo)
    }
}
