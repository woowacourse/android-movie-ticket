package woowacourse.movie.uimodel

import movie.data.TicketCount
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieDetailUi(
    val title: String,
    val count: TicketCount,
    val date: LocalDate,
    val time: LocalTime,
) : Serializable
