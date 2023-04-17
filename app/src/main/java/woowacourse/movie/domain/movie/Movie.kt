package woowacourse.movie.domain.movie

import woowacourse.movie.domain.PlayingTimes
import java.time.LocalDate

data class Movie(
    val title: String,
    val playingTimes: PlayingTimes,
    val runningTime: Int,
    val description: String
) {
    companion object {
        fun of(
            title: String,
            startDate: LocalDate,
            endDate: LocalDate,
            runningTime: Int,
            description: String
        ): Movie {
            return Movie(title, PlayingTimes(startDate, endDate), runningTime, description)
        }
    }
}
