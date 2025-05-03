package woowacourse.movie.model.ticket.seat

sealed class SeatToggleResult {
    data class Added(
        val seat: Seat,
    ) : SeatToggleResult()

    data class Removed(
        val seat: Seat,
    ) : SeatToggleResult()
}
