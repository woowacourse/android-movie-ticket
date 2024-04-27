package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.ScreenRepository

class SeatReservationPresenter(
    private val view: SeatReservationContract.View,
    private val screenRepository: ScreenRepository,
) : SeatReservationContract.Presenter {
    override fun loadSeats(screenId: Int) {
        val seats = screenRepository.seats(screenId)
        view.showSeats(seats)
    }

    override fun reserve(screenId: Int, seat: Seats) {
        TODO("Not yet implemented")
    }
}
