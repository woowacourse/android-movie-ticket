package woowacourse.movie.domain.model.reservation

class MovieTicket(
    val ticketId: Long,
    val screeningMovieInfo: ScreeningMovieInfo,
    val reservationInfo: ReservationInfo,
)
