package woowacourse.movie.contract.reservation

import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.domain.reservation.ScreeningContent

interface ScreeningContract {
    interface Presenter {
        fun presentScreenings()

        fun selectScreening(screening: Screening)
    }

    interface View {
        fun setScreenings(screenings: List<ScreeningContent>)

        fun navigateToReservationScreen(screening: Screening)
    }
}
