package woowacourse.movie.presenter.screening

import woowacourse.movie.contract.ScreeningContract
import woowacourse.movie.data.screening.LocalScreenings
import woowacourse.movie.data.screening.Screenings

class ScreeningPresenter(
    private val view: ScreeningContract.View,
    private val screenings: Screenings = LocalScreenings(),
) : ScreeningContract.Presenter {
    override fun updateScreenings() {
        view.setScreenings(screenings.value)
    }
}
