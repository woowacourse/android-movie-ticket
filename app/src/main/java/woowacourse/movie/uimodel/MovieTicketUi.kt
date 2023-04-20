package woowacourse.movie.uimodel

import movie.data.TicketCount
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieTicketUi(
    val totalPrice: Int,
    val count: TicketCount,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val seats: List<String>,
) : Serializable
