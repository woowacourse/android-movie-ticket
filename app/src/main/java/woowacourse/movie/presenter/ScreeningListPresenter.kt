package woowacourse.movie.presenter

import woowacourse.movie.adapter.ScreeningAdapter
import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ScreeningRepository

class ScreeningListPresenter(
    private val screeningListView: ScreeningListContract.View,
    screeningRepository: ScreeningRepository = PseudoScreeningRepository,
    private val screeningAdapter: ScreeningAdapter,
) : ScreeningListContract.Presenter {
    private val screenings = screeningRepository.getScreenings()

    init {
        screeningAdapter.onClick = ::selectScreening
        loadScreenings()
    }

    override fun loadScreenings() {
        screeningAdapter.setScreening(screenings)
    }

    override fun selectScreening(screening: Screening) {
        screeningListView.navigateToMovieDetail(screening)
    }
}
