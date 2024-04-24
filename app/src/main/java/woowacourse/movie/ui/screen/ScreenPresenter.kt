package woowacourse.movie.ui.screen

import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.ui.toPreviewUI

class ScreenPresenter(
    private val view: ScreenContract.View,
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository,
) : ScreenContract.Presenter {
    override fun loadScreens() {
        view.showScreens(
            screenRepository.load()
                .map { screen ->
                    screen.toPreviewUI(image = movieRepository.imageSrc(screen.movie.id))
                },
        )
    }
}
