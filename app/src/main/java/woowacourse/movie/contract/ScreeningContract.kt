package woowacourse.movie.contract

import woowacourse.movie.domain.screening.Screening

interface ScreeningContract {
    interface Presenter {
        fun updateScreenings()
    }

    interface View {
        fun setScreenings(screenings: List<Screening>)
    }
}
