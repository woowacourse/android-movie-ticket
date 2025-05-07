package woowacourse.movie.contract.reservation

import woowacourse.movie.domain.reservation.Seat
import java.time.LocalDateTime

interface SeatSelectionContract {
    interface Presenter {
        fun fetchAvailableSeats()

        fun fetchScreeningDetail()

        fun selectSeat(seat: Seat)

        fun reserve()

        fun confirmReservation()

        fun selectedSeats(): Set<Seat>
    }

    interface View {
        fun setSeats(
            seats: Set<Seat>,
            selectedSeats: Set<Seat>,
        )

        fun setTitle(title: String)

        fun setPrice(price: Int)

        fun setSeatIsSelected(
            seat: Seat,
            selected: Boolean,
        )

        fun setConfirmEnabled(isEnabled: Boolean)

        fun requestConfirm()

        fun navigateToTicketScreen(
            title: String,
            count: Int,
            showtime: LocalDateTime,
            seats: Set<Seat>,
        )
    }
}
