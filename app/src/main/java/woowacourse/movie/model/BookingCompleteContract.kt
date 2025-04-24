package woowacourse.movie.model

import android.os.Bundle

interface BookingCompleteContract {
    interface Presenter {
        fun initializeData(savedInstanceState: Bundle?)
    }

    interface View {
        fun showBookingCompleteResult(result: BookingResult)

        fun showToastErrorAndFinish(message: String)
    }
}
