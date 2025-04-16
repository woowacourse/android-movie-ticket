package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: RunningTime,
    val imageUrl: Int,
) : Serializable {
    companion object {
        const val TICKET_PRICE = 13_000
    }
}
