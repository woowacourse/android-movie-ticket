package woowacourse.movie.feature.movieSelect

import woowacourse.movie.feature.movieSelect.adapter.MovieSelectViewData
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData

interface MovieSelectContract {
    interface View {
        fun updateMovieList(movieSelectViewDatas: List<MovieSelectViewData>)

        fun navigateToReservationView(screeningData: ScreeningData)
    }

    interface Presenter {
        fun loadMovieList()

        fun navigateToReservationView(screeningData: ScreeningData)
    }
}
