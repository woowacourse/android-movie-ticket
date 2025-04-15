package woowacourse.movie.domain

import java.time.LocalDate

data class Movie(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: RunningTime,
    val imageUrl: Int,
) {
    companion object {
        const val TICKET_PRICE = 13_000
    }
}
