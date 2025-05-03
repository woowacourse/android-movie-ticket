package woowacourse.movie.feature.main

import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData

interface MainContract {
    interface View {
        fun initMovieListUI(
            screenings: List<Screening>,
            ads: List<Advertisement>,
        )

        fun navigateToReservationUI(screeningData: ScreeningData)
    }
}
