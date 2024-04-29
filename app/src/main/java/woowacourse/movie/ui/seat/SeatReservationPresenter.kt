package woowacourse.movie.ui.seat

import android.util.Log
import android.view.View
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.repository.ReservationRepository
import woowacourse.movie.domain.repository.ScreenRepository
import java.lang.IllegalStateException

class SeatReservationPresenter(
    private val view: SeatReservationContract.View,
    private val screenRepository: ScreenRepository,
    private val reservationRepository: ReservationRepository,
) : SeatReservationContract.Presenter {
    private val selectedSeats = mutableListOf<Seat>()
    private var ticketCount = 0 // 받아오기
    private var seats: Seats = Seats()

    override fun loadSeats(screenId: Int) {
        val seats = screenRepository.seats(screenId)
        this.seats = seats
        view.showSeats(seats)
    }

    override fun loadTimeReservations(timeReservationId: Int) {
        val timeReservation = reservationRepository.loadTimeReservation(timeReservationId)
        ticketCount = timeReservation.ticket.count

        view.showTimeReservations(
            reservationRepository.loadTimeReservation(timeReservationId),
        )
    }

    override fun selectSeat(
        position: Position,
        seatView: View,
    ) {
        val seat = seats.findSeat(position)
        Log.d("selectedSeats", "before add or remove $selectedSeats")

        if (selectedSeats.contains(seat)) {
            seatView.isSelected = !seatView.isSelected // 선택 상태 토글
            selectedSeats.remove(seat)
        } else {
            if (selectedSeats.size >= ticketCount) {
                view.showToast(IllegalArgumentException("exceed ticket count that can be reserved."))
            } else {
                seatView.isSelected = !seatView.isSelected // 선택 상태 토글
                selectedSeats.add(seat)
            }
        }
        Log.d("selectedSeats", "after add or remove $selectedSeats")

        view.showTotalPrice(Seats(selectedSeats))
        if (selectedSeats.count() == ticketCount) {
            view.activateReservation(true)
        } else {
            view.activateReservation(false)
        }
    }

    private fun screen(id: Int): Screen {
        screenRepository.findById(id = id).onSuccess { screen ->
            return screen
        }.onFailure { e ->
            throw e
        }
        throw IllegalStateException("예기치 못한 오류")
    }

    override fun reserve(screenId: Int) {
        val timeReservation = reservationRepository.loadTimeReservation(screenId)

        reservationRepository.save(
            screen(screenId),
            Seats(selectedSeats),
            timeReservation.dateTime,
        ).onSuccess { id ->
            view.navigateToCompleteReservation(id)
        }.onFailure { e ->
            view.showToast(e)
        }
    }
}
