package woowacourse.movie.domain.model

data class TimeReservation(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
    val dateTime: DateTime,
) {
    companion object {
        val NULL = TimeReservation(0, Screen.NULL, Ticket(1), DateTime.NULL)
    }
}
