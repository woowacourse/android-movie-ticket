package woowacourse.movie.uimodel

import movie.TicketCount
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieTicketUi(
    val eachPrice: Int,
    val count: TicketCount,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
) : Serializable
