package woowacourse.movie.presenter

import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.main.MainView
import woowacourse.movie.view.model.Poster
import woowacourse.movie.view.model.ScreeningData

class MainPresenter(
    private val view: MainView,
) {
    fun initMainUI() {
        val screenings: List<Screening> = Screening.getDefaultScreenings()
        view.initMovieListUI(screenings)
    }

    fun navigateToReservationUI(
        screening: Screening,
        poster: Poster,
    ) {
        view.navigateToReservationUI(convertScreeningData(screening, poster))
    }

    private fun convertScreeningData(
        screening: Screening,
        poster: Poster,
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
