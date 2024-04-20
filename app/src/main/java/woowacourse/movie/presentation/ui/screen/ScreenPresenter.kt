package woowacourse.movie.presentation.ui.screen

import woowacourse.movie.domain.repository.ScreenRepository

class ScreenPresenter(
    private val view: ScreenContract.View,
    private val repository: ScreenRepository,
) : ScreenContract.Presenter {
    override fun loadScreens() {
        val screens = repository.load()
        view.showScreens(screens)
    }

    override fun onScreenClick(id: Int) {
        view.navigateToDetail(id)
    }
}
