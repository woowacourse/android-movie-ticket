package woowacourse.movie.booking.complete

interface BookingCompleteContract {
    interface View {
        fun showTicketInfo(uiModel: BookingCompleteUiModel)
    }

    interface Presenter {
        fun loadTicketInfo(uiModel: BookingCompleteUiModel)
    }
}
