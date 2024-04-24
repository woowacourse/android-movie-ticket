package woowacourse.movie.presenter

import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ScreeningRepository

class ScreeningListPresenter(
    private val screeningListView: ScreeningListContract.View,
    screeningRepository: ScreeningRepository = PseudoScreeningRepository,
) : ScreeningListContract.Presenter {
    private val screenings = screeningRepository.getScreenings()

    override fun loadScreenings() {
        screeningListView.displayScreenings(screenings)
    }

    override fun selectScreening(screeningId: Int) {
        screeningListView.navigateToScreeningDetail(screeningId)
    }
}
