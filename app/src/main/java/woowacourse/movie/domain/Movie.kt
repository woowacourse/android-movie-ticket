package woowacourse.movie.domain

import java.io.Serializable

data class Movie(
    val title: String,
    val screeningDate: ScreeningDate,
    val runningTime: RunningTime,
    val imageUrl: Int,
) : Serializable {
    companion object {
        const val TICKET_PRICE = 13_000
    }
}
