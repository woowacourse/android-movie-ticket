package woowacourse.movie.presenter

import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ScreeningRepository

class ScreeningListPresenter(
    private val view: ScreeningListContract.View,
    screeningRepository: ScreeningRepository = PseudoScreeningRepository(),
) : ScreeningListContract.Presenter {
    private val screenings = screeningRepository.getScreenings()

    override fun loadScreenings() {
        view.displayScreenings(screenings)
    }

    override fun selectScreening(screeningId: Int) {
        view.navigateToScreeningDetail(screeningId)
    }
}
