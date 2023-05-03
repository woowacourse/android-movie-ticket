package woowacourse.movie.view.activities.screeningdetail

import woowacourse.movie.domain.Screening

interface ScreeningDetailContract {
    interface Presenter {
        fun loadScreeningData(screeningId: Long)
    }

    interface View {
        fun setScreening(screening: Screening)
    }
}
