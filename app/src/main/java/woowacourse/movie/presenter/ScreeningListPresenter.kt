package woowacourse.movie.presenter

import woowacourse.movie.adapter.MovieAdapter
import woowacourse.movie.contract.ScreeningListContract
import woowacourse.movie.repository.PseudoScreeningRepository
import woowacourse.movie.repository.ScreeningRepository

class ScreeningListPresenter(
    private val screeningListView: ScreeningListContract.View,
    screeningRepository: ScreeningRepository = PseudoScreeningRepository(),
    val movieAdapter: MovieAdapter,
) : ScreeningListContract.Presenter {
    private val screenings = screeningRepository.getScreenings()

    init {
        movieAdapter.onClick = ::selectScreening
        loadScreenings()
    }

    override fun loadScreenings() {
        movieAdapter.setScreening(screenings)
    }

    override fun selectScreening(position: Int) {
        screeningListView.navigateToMovieDetail(screenings[position])
    }
}
