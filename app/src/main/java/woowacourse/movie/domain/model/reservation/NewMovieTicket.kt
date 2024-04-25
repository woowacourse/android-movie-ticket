package woowacourse.movie.domain.model.reservation

class NewMovieTicket(
    val ticketId: Long,
    val screeningMovieInfo: ScreeningMovieInfo,
    val reservationInfo: ReservationInfo,
)
