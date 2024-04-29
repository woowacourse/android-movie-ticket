package woowacourse.movie.ui.screen

import woowacourse.movie.R
import woowacourse.movie.domain.model.DrawableImage
import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.domain.repository.MovieRepository
import woowacourse.movie.domain.repository.ScreenRepository
import woowacourse.movie.ui.toPreviewUI
import woowacourse.movie.ui.toUi2

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

    override fun loadScreen2() {
        val screens =
            screenRepository.load()
                .map { screen ->
                    screen.toPreviewUI(image = movieRepository.imageSrc(screen.movie.id)).toUi2()
                }

        val ad = ScreenAd.Advertisement(id = 0, DrawableImage(R.drawable.advertisement))

        view.showScreens2(screens + ad)
    }
}
