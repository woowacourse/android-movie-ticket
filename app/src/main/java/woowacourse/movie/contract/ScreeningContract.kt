package woowacourse.movie.contract

import woowacourse.movie.domain.screening.Screening

interface ScreeningContract {
    interface Presenter {
        fun presentScreenings()

        fun selectScreening(screening: Screening)
    }

    interface View {
        fun setScreenings(screenings: List<Screening>)

        fun navigateToReservationScreen(screening: Screening)
    }
}
