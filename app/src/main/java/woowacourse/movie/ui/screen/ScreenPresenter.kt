package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen2
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.domain.repository.ScreenRepository2
import woowacourse.movie.ui.MoviePreviewUI
import woowacourse.movie.ui.ScreenPreviewUI

class ScreenPresenter(
    private val view: ScreenContract.View,
    private val repository: ScreenRepository,
) : ScreenContract.Presenter {
    override fun loadScreens() {
        val screens = repository.load()
        view.showScreens(screens)
    }
}

class ScreenPresenter2(
    private val view: ScreenContract2.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository2,
) : ScreenContract2.Presenter {
    override fun loadScreens() {
        val screens = screenRepository.load()

        val screenPreviewUIs = screens.map { screen -> screen.toPreviewUI() }
        view.showScreens(screenPreviewUIs)
    }

    private fun Screen2.toPreviewUI() = ScreenPreviewUI(
        id = id,
        moviePreviewUI = movie.toPreviewUI(),
        date = date,
    )

    private fun Movie.toPreviewUI() = MoviePreviewUI(
        title = title,
        image = movieRepository.imageSrc(id),
        runningTime = runningTime,
    )
}
