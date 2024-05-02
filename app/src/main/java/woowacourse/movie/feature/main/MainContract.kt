package woowacourse.movie.feature.main

import woowacourse.movie.feature.main.ui.ScreeningItem

interface MainContract {
    interface View {
        fun displayScreenings(screeningItems: MutableList<ScreeningItem>)

        fun navigateToReservationScreen(screeningId: Long)

        fun updateScreeningList(
            positionStart: Int,
            itemCount: Int,
        )
    }

    interface Presenter {
        fun fetchScreeningList()

        fun selectScreening(screeningId: Long)
    }
}
