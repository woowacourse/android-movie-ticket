package woowacourse.movie.contract.reservation

import woowacourse.movie.domain.reservation.Screening

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
