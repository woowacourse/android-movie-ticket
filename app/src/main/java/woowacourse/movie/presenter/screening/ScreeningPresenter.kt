package woowacourse.movie.presenter.screening

import woowacourse.movie.contract.screening.ScreeningContract
import woowacourse.movie.data.screening.LocalScreenings
import woowacourse.movie.data.screening.Screenings
import woowacourse.movie.domain.screening.Screening

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
