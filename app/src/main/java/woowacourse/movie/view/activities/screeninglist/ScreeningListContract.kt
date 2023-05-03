package woowacourse.movie.view.activities.screeninglist

interface ScreeningListContract {
    interface Presenter {
        fun loadScreenings()
    }

    interface View {
        fun setScreeningList(screeningListViewItemUIStates: List<ScreeningListViewItemUIState>)
    }
}
