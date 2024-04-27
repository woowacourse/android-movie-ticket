package woowacourse.movie.feature.main

import woowacourse.movie.feature.main.ui.ScreeningModel

interface MainContract {
    interface View {
        fun displayScreenings(screeningModels: List<ScreeningModel>)

        fun navigateToReservationScreen(id: Long)
    }

    interface Presenter {
        fun fetchScreeningList()

        fun selectScreening(id: Long)
    }
}
