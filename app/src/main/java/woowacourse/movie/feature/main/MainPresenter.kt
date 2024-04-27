package woowacourse.movie.feature.main

import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.feature.main.ui.toUiModel

class MainPresenter(private val view: MainContract.View, private val repository: ScreeningRepository) :
    MainContract.Presenter {
    override fun fetchScreeningList() {
        val screenings = repository.findAll()
        view.displayScreenings(screenings.map { it.toUiModel() })
    }

    override fun selectScreening(id: Long) {
        view.navigateToReservationScreen(id)
    }
}
