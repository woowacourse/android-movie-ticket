package woowacourse.movie.feature.main

import woowacourse.movie.R
import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.feature.main.ui.toUiModel

class MainPresenter(
    private val view: MainContract.View,
    private val repository: ScreeningRepository,
) :
    MainContract.Presenter {
    override fun fetchScreeningList() {
        val screenings = repository.findAll()
        val adImageResources = listOf(R.drawable.woowa_ad)
        view.displayScreenings(
            screenings.map { it.toUiModel() },
            adImageResources,
        )
    }

    override fun selectScreening(screeningId: Long) {
        view.navigateToReservationScreen(screeningId)
    }
}
