package woowacourse.movie.ui.home

import woowacourse.movie.repository.ScreenListRepository

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {
    override fun onScreenSelected(screenId: Long) {
        view.startReservationActivity(screenId)
    }

    override fun loadScreens(screenListRepository: ScreenListRepository) {
        view.showScreens(screenListRepository)
    }
}
