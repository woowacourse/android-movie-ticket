package woowacourse.movie.presenter

import woowacourse.movie.ui.view.booking.SeatsSelectionContract

class SeatsSelectionPresenter(
    private val view: SeatsSelectionContract.View,
) : SeatsSelectionContract.Presenter {
    override fun onConfirm() {
        view.navigateToBookingSummary()
    }
}
