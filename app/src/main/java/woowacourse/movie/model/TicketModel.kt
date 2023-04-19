package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

data class TicketModel(
    val title: String,
    val playingDate: LocalDate,
    val playingTime: LocalTime,
    val count: Int,
    val price: Int
) : java.io.Serializable
