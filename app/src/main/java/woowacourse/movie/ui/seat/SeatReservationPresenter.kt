package woowacourse.movie.ui.seat

import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository

class SeatReservationPresenter(
    private val view: SeatReservationContract.View,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : SeatReservationContract.Presenter {
    private val selectedSeats = mutableListOf<Seat>()
    private val ticketCount = 0 // 받아오기

    override fun loadSeats(screenId: Int) {
        val seats = screenRepository.seats(screenId)
        view.showSeats(seats)
    }

    override fun loadTimeReservations(timeReservationId: Int) {
        view.showTimeReservations(
            reservationRepository.loadTimeReservation(timeReservationId)
        )
    }

    override fun selectSeat(seat: Seat) {
        TODO("Not yet implemented")
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
