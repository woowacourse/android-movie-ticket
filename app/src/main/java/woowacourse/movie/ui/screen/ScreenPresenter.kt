package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.ui.MoviePreviewUI
import woowacourse.movie.ui.ScreenPreviewUI

class ScreenPresenter(
    private val view: ScreenContract.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository,
) : ScreenContract.Presenter {
    override fun loadScreens() {
        val screens = screenRepository.load()

        val screenPreviewUIs = screens.map { screen -> screen.toPreviewUI() }
        view.showScreens(screenPreviewUIs)
    }

    private fun Screen.toPreviewUI() = ScreenPreviewUI(
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
