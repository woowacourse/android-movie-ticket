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
) : Serializable {

    companion object {
        val EMPTY_STATE: MovieTicketUi = MovieTicketUi(
            0,
            TicketCount(0),
            "",
            LocalDate.MIN,
            LocalTime.MIN,
        )
    }
}
