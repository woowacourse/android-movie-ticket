package woowacourse.movie.domain.model

class ReservationInfo(
    val reservationCount: Int,
    seatingChart: SeatingChart,
) {
    val selectedSeats = SelectedSeats(seatingChart, reservationCount)
}
