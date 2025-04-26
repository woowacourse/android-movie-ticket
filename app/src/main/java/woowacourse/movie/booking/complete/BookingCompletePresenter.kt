package woowacourse.movie.booking.complete

import android.os.Bundle
import woowacourse.movie.booking.detail.TicketUiModel

class BookingCompletePresenter(
    private val view: BookingCompleteContract.View,
    private val ticket: TicketUiModel?,
) : BookingCompleteContract.Presenter {
    override fun initializeData(savedInstanceState: Bundle?) {
        if (ticket == null) {
            view.showToastErrorAndFinish("영화 정보를 불러올 수 없습니다.")
            return
        }
        view.showBookingCompleteResult(ticket)
    }
}
