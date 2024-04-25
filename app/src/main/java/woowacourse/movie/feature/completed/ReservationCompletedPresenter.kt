package woowacourse.movie.feature.completed

import woowacourse.movie.data.MockReservationRepository
import woowacourse.movie.data.ReservationRepository
import woowacourse.movie.feature.reservation.ui.toUiModel

class ReservationCompletedPresenter(
    private val view: ReservationCompletedContract.View,
    private val repository: ReservationRepository = MockReservationRepository,
) :
    ReservationCompletedContract.Presenter {
    override fun fetchReservationDetails(id: Long) {
        val reservation = repository.find(id) ?: return
        view.initializeReservationDetails(reservation.toUiModel())
    }
}
