package woowacourse.movie.view.main

import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData

interface MainView {
    fun initMovieListUI(
        screenings: List<Screening>,
        ads: List<Advertisement>,
    )

    fun navigateToReservationUI(screeningData: ScreeningData)
}
