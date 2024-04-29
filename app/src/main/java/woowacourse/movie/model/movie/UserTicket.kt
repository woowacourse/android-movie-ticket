package woowacourse.movie.model.movie

import java.time.LocalDateTime

data class UserTicket(
    val title: String,
    val screeningStartDateTime: LocalDateTime,
    val reservationDetail: ReservationDetail,
    val id: Long = 0,
)
