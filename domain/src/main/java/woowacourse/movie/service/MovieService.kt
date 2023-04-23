package woowacourse.movie.service

import woowacourse.movie.domain.*
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

object MovieService {

    fun save(title: String, runningTime: Int, summary: String): Long {
        val movie =
            Movie(
                MovieRepository.nextId,
                title,
                Minute(runningTime),
                summary
            )

        MovieRepository.save(movie)

        return movie.id
    }

    fun addScreening(movieId: Long, screeningDateTime: LocalDateTime) {
        val movie = MovieRepository.findById(movieId)
        movie.addScreening(ScreeningInfoOfMovie(screeningDateTime))
        MovieRepository.save(movie)
    }
}
