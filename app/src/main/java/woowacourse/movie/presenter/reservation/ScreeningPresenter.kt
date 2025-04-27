package woowacourse.movie.presenter.reservation

import woowacourse.movie.contract.reservation.ScreeningContract
import woowacourse.movie.data.reservation.LocalScreenings
import woowacourse.movie.data.reservation.Screenings
import woowacourse.movie.domain.reservation.Screening

class ScreeningPresenter(
    private val view: ScreeningContract.View,
    private val screenings: Screenings = LocalScreenings(),
) : ScreeningContract.Presenter {
    override fun presentScreenings() {
        view.setScreenings(screenings.value)
    }

    override fun selectScreening(screening: Screening) {
        view.navigateToReservationScreen(screening)
    }
}
