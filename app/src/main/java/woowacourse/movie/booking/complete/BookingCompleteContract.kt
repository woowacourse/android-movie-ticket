package woowacourse.movie.booking.complete

import android.os.Bundle
import woowacourse.movie.booking.detail.TicketUiModel

interface BookingCompleteContract {
    interface Presenter {
        fun initializeData(savedInstanceState: Bundle?)
    }

    interface View {
        fun showBookingCompleteResult(result: TicketUiModel)

        fun showToastErrorAndFinish(message: String)
    }
}
