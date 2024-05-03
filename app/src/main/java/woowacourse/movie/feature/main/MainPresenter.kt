package woowacourse.movie.feature.main

import woowacourse.movie.data.ScreeningRepository
import woowacourse.movie.feature.main.ui.ScreeningItem

class MainPresenter(
    private val view: MainContract.View,
    private val repository: ScreeningRepository,
) :
    MainContract.Presenter {
    private var screenings: MutableList<ScreeningItem> = repository.findAll().toMutableList()

    init {
        view.displayScreenings(screenings)
    }

    override fun fetchScreeningList() {
        val newScreenings: MutableList<ScreeningItem> = repository.findAll().toMutableList()
        screenings.addAll(newScreenings)
        val positionStart = newScreenings.size
        val itemCount = screenings.size
        view.updateScreeningList(positionStart, itemCount)
    }

    override fun selectScreening(screeningId: Long) {
        view.navigateToReservationScreen(screeningId)
    }
}
