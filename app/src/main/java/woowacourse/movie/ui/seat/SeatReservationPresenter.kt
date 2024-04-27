package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository

class SeatReservationPresenter(
    private val view: SeatReservationContract.View,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : SeatReservationContract.Presenter {
    override fun loadSeats(screenId: Int) {
        val seats = screenRepository.seats(screenId)
        view.showSeats(seats)
    }

    override fun reserve(
        screen: Screen,
        seats: Seats,
    ) {
        reservationRepository.save(screen, seats)
            .onSuccess {
                view.navigateToCompleteReservation(it)
            }
            .onFailure {
                view.showSeatReservationFail(it)
            }
    }
}
