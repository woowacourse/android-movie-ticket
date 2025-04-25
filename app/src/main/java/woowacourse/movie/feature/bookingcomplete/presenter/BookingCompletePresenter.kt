package woowacourse.movie.feature.bookingcomplete.presenter

import woowacourse.movie.feature.bookingcomplete.contract.BookingCompleteContract
import woowacourse.movie.feature.bookingcomplete.contract.BookingCompleteContract.Presenter
import woowacourse.movie.feature.model.BookingInfoUiModel

class BookingCompletePresenter(
    private val view: BookingCompleteContract.View,
) : Presenter {
    override fun onCreateView(bookingInfo: BookingInfoUiModel) {
        view.showBookingResult(bookingInfo)
    }

    override fun onBackButtonClicked() {
        view.navigateToBack()
    }
}
