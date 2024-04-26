package woowacourse.movie.model.movie

data class UserTicket(
    val title: String,
    val date: String,
    val time: String,
    val reservationDetail: ReservationDetail,
    val id: Long = 0,
)
