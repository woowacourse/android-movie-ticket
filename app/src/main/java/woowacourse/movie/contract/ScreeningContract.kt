package woowacourse.movie.contract

import woowacourse.movie.domain.screening.Screening

interface ScreeningContract {
    interface Presenter {
        fun presentScreenings()
    }

    interface View {
        fun setScreenings(screenings: List<Screening>)
    }
}
