package woowacourse.movie.feature

import io.mockk.every
import io.mockk.just
import io.mockk.runs
import woowacourse.movie.feature.reservation.MovieReservationPresenter
import woowacourse.movie.feature.seat.SeatSelectContract
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SelectedSeats

fun MovieReservationPresenter.setUpReservationCount(count: Int) {
    initializeReservationCount()
    repeat(count - 1) { increaseReservationCount() }
}

fun SeatSelectContract.View.setUpForSelectSeat() {
    every { selectSeat(any(), any(), any()) } just runs
    every { showCannotSelectSeat() } just runs
    every { unselectSeat(any(), any()) } just runs
    every { updateReservationAmount(any()) } just runs
    every { initializeSeatTable(any()) } just runs
}

fun SelectedSeats(
    count: Int,
    vararg seat: Seat,
): SelectedSeats {
    return SelectedSeats(ReservationCount(count)).apply {
        seat.forEach { select(it) }
    }
}
