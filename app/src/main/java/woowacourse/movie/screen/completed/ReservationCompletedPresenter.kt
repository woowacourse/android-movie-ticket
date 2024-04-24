package woowacourse.movie.screen.completed

import woowacourse.movie.data.MockReservationRepository

class ReservationCompletedPresenter(private val view: ReservationCompletedContract.View) :
    ReservationCompletedContract.Presenter {
    override fun fetchReservationDetails(id: Long) {
        val reservation = MockReservationRepository.find(id) ?: return
        view.initializeReservationDetails(reservation)
    }
}
