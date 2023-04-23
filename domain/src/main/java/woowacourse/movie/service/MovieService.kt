package woowacourse.movie.service

import woowacourse.movie.domain.*
import woowacourse.movie.repository.MovieHouseRepository
import woowacourse.movie.repository.MovieRepository
import java.time.LocalDateTime

object MovieService {

    private const val MOVIE_NOT_EXISTS_ERROR = "아이디가 %d인 영화가 존재하지 않습니다."
    private const val MOVIE_HOUSE_NOT_EXISTS_ERROR = "아이디가 %d인 영화관이 존재하지 않습니다."

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

    fun addScreening(movieId: Long, screeningDateTime: LocalDateTime, movieHouseId: Long = 1) {
        val movie = MovieRepository.findById(movieId)
            ?: throw IllegalArgumentException(MOVIE_NOT_EXISTS_ERROR.format(movieId))
        val movieHouse = MovieHouseRepository.findById(movieHouseId)
            ?: throw IllegalArgumentException(MOVIE_HOUSE_NOT_EXISTS_ERROR.format(movieHouseId))

        movie.addScreening(ScreeningInfoOfMovie(screeningDateTime, movieHouse))
    }
}
