package woowacourse.movie.view.activities.screeningdetail

interface ScreeningDetailContract {
    interface Presenter {
        fun loadScreeningData(screeningId: Long)
    }

    interface View {
        fun setScreening(screeningDetailUIState: ScreeningDetailUIState)
    }
}
