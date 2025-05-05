package woowacourse.movie.feature.bookingcomplete.presenter

import woowacourse.movie.feature.bookingcomplete.contract.BookingCompleteContract
import woowacourse.movie.feature.bookingcomplete.contract.BookingCompleteContract.Presenter
import woowacourse.movie.feature.model.BookingInfoUiModel

class BookingCompletePresenter(
    private val view: BookingCompleteContract.View,
) : Presenter {
    override fun prepareBookingInfo(bookingInfo: BookingInfoUiModel) {
        view.showBookingResult(bookingInfo)
    }

    override fun quitBookingInfo() {
        view.navigateToBack()
    }
}
