package woowacourse.movie.dao

import woowacourse.movie.domain.Movie
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.minutes


class MovieDao {
    fun movies(): Set<Movie> {
        return File("../app/src/main/java/woowacourse/movie/data/movie_ticket.md").readLines()
            .filterNot { it.startsWith("title") }
            .map { movie(it)}
            .toSet()
    }

    private fun movie(input:String):Movie {
        val (title, posterUrl, startDate, endDate, runningTime) = input.split(",").map {it.trim()}
        val startDateTime = LocalDateTime.parse(startDate)
        val endDateTime = LocalDateTime.parse(endDate)
        val parsedRunningTime = runningTime.toInt().minutes
        return Movie(title, posterUrl, startDateTime, endDateTime, parsedRunningTime)
    }

}