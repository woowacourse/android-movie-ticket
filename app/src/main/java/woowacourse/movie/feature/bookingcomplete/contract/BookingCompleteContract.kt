package woowacourse.movie.feature.bookingcomplete.contract

import woowacourse.movie.feature.model.BookingInfoUiModel

interface BookingCompleteContract {
    interface View {
        fun showBookingResult(bookingInfo: BookingInfoUiModel)

        fun navigateToBack()
    }

    interface Presenter {
        fun onCreateView(bookingInfo: BookingInfoUiModel)

        fun onBackButtonClicked()
    }
}
