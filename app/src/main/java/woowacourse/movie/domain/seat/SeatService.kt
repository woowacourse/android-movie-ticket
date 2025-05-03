package woowacourse.movie.domain.seat

class SeatService {
    private val seats = mutableSetOf<Seat>()

    fun getSeatSate(
        seat: Seat,
        headCount: Int,
    ): SeatState =
        when {
            seat in seats -> {
                seats.remove(seat)
                SeatState.DESELECTED
            }

            seats.size >= headCount -> SeatState.LIMIT
            else -> {
                seats.add(seat)
                SeatState.SELECTED
            }
        }
}
