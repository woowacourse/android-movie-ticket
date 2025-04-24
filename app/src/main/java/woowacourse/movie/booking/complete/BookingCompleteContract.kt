package woowacourse.movie.booking.complete

import android.os.Bundle
import woowacourse.movie.model.BookingResult

interface BookingCompleteContract {
    interface Presenter {
        fun initializeData(savedInstanceState: Bundle?)
    }

    interface View {
        fun showBookingCompleteResult(result: BookingResult)

        fun showToastErrorAndFinish(message: String)
    }
}