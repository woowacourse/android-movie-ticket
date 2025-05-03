package woowacourse.movie.feature.main

import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ImageResource
import woowacourse.movie.view.model.ScreeningData

class MainPresenter(
    private val view: MainContract.View,
) {
    fun initMainUI() {
        val screenings: List<Screening> = Screening.Companion.getDefaultScreenings()
        val ads: List<Advertisement> = Advertisement.Companion.getDefaultAds()
        view.initMovieListUI(screenings, ads)
    }

    fun navigateToReservationUI(
        screening: Screening,
        poster: ImageResource,
    ) {
        view.navigateToReservationUI(convertScreeningData(screening, poster))
    }

    private fun convertScreeningData(
        screening: Screening,
        poster: ImageResource,
    ): ScreeningData =
        ScreeningData(
            title = screening.title,
            startDate = screening.period.start,
            endDate = screening.period.endInclusive,
            movieId = screening.movieId,
            runningTime = screening.runningTime,
            poster = poster,
        )
}
