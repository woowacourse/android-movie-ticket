package woowacourse.movie.model

data class ReservationState(
    val movie: Movie,
    val movieDate: MovieDate,
    val movieTime: MovieTime,
    val ticketCount: TicketCount,
    val timeTable: List<Int>,
)
