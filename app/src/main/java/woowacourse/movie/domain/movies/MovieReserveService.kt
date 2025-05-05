package woowacourse.movie.domain.movies

import java.time.LocalDateTime

class MovieReserveService {
    fun createMovieToReserve(
        movieId: Int,
        screeningDateTime: LocalDateTime,
        headCount: Int,
    ): MovieToReserve = MovieToReserve(movieId, screeningDateTime, headCount)
}
