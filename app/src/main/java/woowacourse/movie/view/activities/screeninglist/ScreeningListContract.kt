package woowacourse.movie.view.activities.screeninglist

import woowacourse.movie.domain.Screening

interface ScreeningListContract {
    interface Presenter {
        fun loadScreenings()
    }

    interface View {
        fun setScreeningList(screenings: List<Screening>)
    }
}
