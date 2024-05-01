package woowacourse.movie.feature.main

import woowacourse.movie.feature.main.ui.ScreeningItem

interface MainContract {
    interface View {
        fun displayScreenings(screeningItems: List<ScreeningItem>)

        fun navigateToReservationScreen(screeningId: Long)
    }

    interface Presenter {
        fun fetchScreeningList()

        fun selectScreening(screeningId: Long)
    }
}
