package woowacourse.movie.contract

import woowacourse.movie.model.screening.Screening

interface ScreeningListContract {
    interface View {
        fun displayScreenings(screenings: List<Screening>)

        fun navigateToScreeningDetail(screeningId: Int)
    }

    interface Presenter {
        fun loadScreenings()

        fun selectScreening(screeningId: Int)
    }
}
