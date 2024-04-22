package woowacourse.movie.contract

import woowacourse.movie.model.screening.Screening

interface ScreeningListContract {
    interface View {
        fun navigateToMovieDetail(screening: Screening)
    }

    interface Presenter {
        fun loadScreenings()
        fun selectScreening(position: Int)
    }
}
