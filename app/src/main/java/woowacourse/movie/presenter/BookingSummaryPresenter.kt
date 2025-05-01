package woowacourse.movie.presenter

import woowacourse.movie.ui.view.booking.BookingSummaryContract

class BookingSummaryPresenter(
    private val view: BookingSummaryContract.View,
) : BookingSummaryContract.Presenter {
    override fun loadBookingSummary() {
        view.showBookingSummary()
    }
}
