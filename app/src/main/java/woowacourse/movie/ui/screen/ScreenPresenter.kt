package woowacourse.movie.ui.screen

import woowacourse.movie.domain.model.IMovie
import woowacourse.movie.domain.model.IScreen
import woowacourse.movie.domain.model.Image
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

        val screenPreviewUIs = screens.map { screen -> screen.toPreviewUI(image = movieRepository.imageSrc(screen.movie.id)) }
        view.showScreens(screenPreviewUIs)
    }
}

private fun IScreen.toPreviewUI(image: Image<Any>) =
    ScreenPreviewUI(
        id = id,
        moviePreviewUI = movie.toPreviewUI(image),
        date = date,
    )

private fun IMovie.toPreviewUI(image: Image<Any>) =
    MoviePreviewUI(
        title = title,
        image = image,
        runningTime = runningTime,
    )
