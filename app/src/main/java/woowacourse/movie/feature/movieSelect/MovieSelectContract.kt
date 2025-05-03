package woowacourse.movie.feature.movieSelect

import woowacourse.movie.feature.movieSelect.adapter.AdvertisementData
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData

interface MovieSelectContract {
    interface View {
        fun updateMovieList(
            screeningDataList: List<ScreeningData>,
            adsDataList: List<AdvertisementData>,
        )

        fun navigateToReservationView(screeningData: ScreeningData)
    }

    interface Presenter {
        fun loadMovieList()

        fun navigateToReservationView(screeningData: ScreeningData)
    }
}
