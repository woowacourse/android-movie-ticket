package woowacourse.movie.domain.model

class NewMovieTicket(
    val ticketId: Long,
    val screeningMovieInfo: ScreeningMovieInfo,
    val reservationInfo: ReservationInfo,
)
