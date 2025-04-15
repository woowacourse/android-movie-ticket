package woowacourse.movie.dao

import woowacourse.movie.domain.Movie
import java.io.File
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.minutes

class MovieDao {
    fun movies(): Map<String, Movie> {
        val map = mutableMapOf<String, Movie>()
        File("../app/src/main/java/woowacourse/movie/data/movie_ticket.md").readLines()
            .drop(1)
            .forEach {
                val (title, movie) = movie(it)
                map[title] = movie
            }
        return map.toMap()
    }

    private fun movie(input: String): Pair<String, Movie> {
        val (title, posterUrl, startDate, endDate, runningTime) = input.split(",").map { it.trim() }
        val startDateTime = LocalDateTime.parse(startDate)
        val endDateTime = LocalDateTime.parse(endDate)
        val parsedRunningTime = runningTime.toInt().minutes
        return Pair(
            title,
            Movie(title, posterUrl, startDateTime, endDateTime, parsedRunningTime),
        )
    }
}
