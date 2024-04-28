package woowacourse.movie.feature.main

import woowacourse.movie.feature.main.ui.ScreeningModel

interface MainContract {
    interface View {
        fun displayScreenings(
            screeningModels: List<ScreeningModel>,
            adImageResources: List<Int>,
        )

        fun navigateToReservationScreen(screeningId: Long)
    }

    interface Presenter {
        fun fetchScreeningList()

        fun selectScreening(screeningId: Long)
    }
}
