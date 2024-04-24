package woowacourse.movie.feature.completed

import woowacourse.movie.data.MockReservationRepository
import woowacourse.movie.feature.reservation.ui.toUiModel

class ReservationCompletedPresenter(private val view: ReservationCompletedContract.View) :
    ReservationCompletedContract.Presenter {
    override fun fetchReservationDetails(id: Long) {
        val reservation = MockReservationRepository.find(id) ?: return
        view.initializeReservationDetails(reservation.toUiModel())
    }
}
