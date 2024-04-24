package woowacourse.movie.contract

import woowacourse.movie.model.screening.Screening

interface ScreeningListContract {
    interface View {
        fun navigateToMovieDetail(screeningId: Int)
    }

    interface Presenter {
        fun loadScreenings()

        fun selectScreening(screeningId: Int)
    }
}
