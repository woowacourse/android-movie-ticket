package woowacourse.movie.view.activities.screeningdetail

interface ScreeningDetailContract {
    interface Presenter {
        fun loadScreeningData()
    }

    interface View {
        fun setScreening(screeningDetailUIState: ScreeningDetailUIState)
    }
}
