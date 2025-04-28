package woowacourse.movie.domain

import woowacourse.movie.uiModel.Ticket
import java.time.LocalDate
import java.time.LocalTime

class Ticket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val seats: MutableList<String>,
    var money: Int,
) {
    fun toDto(): Ticket = Ticket(title, date.toString(), time.toString(), seats, seats.size, money)
}
