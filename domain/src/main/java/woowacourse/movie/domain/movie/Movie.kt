package woowacourse.movie.domain.movie

import java.time.LocalDate

data class Movie(
    val title: String,
    val playingDateTimes: PlayingDateTimes,
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
            return Movie(title, PlayingDateTimes(startDate, endDate), runningTime, description)
        }
    }
}
