package woowacourse.movie.feature.movieSelect

import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ScreeningData

interface MovieSelectContract {
    interface View {
        fun initMovieListUI(
            screenings: List<Screening>,
            ads: List<Advertisement>,
        )

        fun navigateToReservationUI(screeningData: ScreeningData)
    }
}
