package woowacourse.movie.presenter

import woowacourse.movie.ui.view.booking.BookingSummaryContract

class BookingSummaryPresenter(
    private val view: BookingSummaryContract.View,
) {
    fun loadBookingSummary() {
        view.showBookingSummary()
    }
}
