package woowacourse.movie.model

import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate
import java.time.LocalTime

class Ticket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val seats: MutableList<String>,
    var money: Int,
) {
    fun toUIModel(): TicketUIModel = TicketUIModel(title, date.toString(), time.toString(), seats, seats.size, money)
}
