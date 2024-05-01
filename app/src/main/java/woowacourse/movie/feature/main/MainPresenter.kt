package woowacourse.movie.feature.main

import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.feature.main.ui.ScreeningItem

class MainPresenter(
    private val view: MainContract.View,
    private val repository: ScreeningRepository,
) :
    MainContract.Presenter {
    override fun fetchScreeningList() {
        val screenings: List<ScreeningItem> = repository.findAll()
        view.displayScreenings(screenings)
    }

    override fun selectScreening(screeningId: Long) {
        view.navigateToReservationScreen(screeningId)
    }
}
