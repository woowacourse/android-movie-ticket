package woowacourse.movie.model

import woowacourse.movie.uiModel.TicketUIModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Ticket(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val seats: MutableList<String>,
    var money: Int,
) {
    private val formatter = DateTimeFormatter.ofPattern("yyyy.M.d")

    fun toUIModel(): TicketUIModel = TicketUIModel(title, date.format(formatter).toString(), time.toString(), seats, seats.size, money)
}
