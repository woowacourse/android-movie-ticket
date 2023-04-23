package woowacourse.movie.service

import woowacourse.movie.domain.Point
import woowacourse.movie.domain.ScreeningInfoOfMovie
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.repository.MovieHouseRepository
import woowacourse.movie.repository.MovieRepository
import woowacourse.movie.repository.ReservationResultRepository
import java.time.LocalDateTime

object MovieService {

    private const val NOT_EXIST_MOVIE_ERROR = "아이디가 %d인 영화가 존재하지 않습니다."
    private const val NOT_EXIST_MOVIE_HOUSE_ERROR = "아이디가 %d인 영화관이 존재하지 않습니다."

    fun findAllMovies(): List<MovieDto> {
        val movies = MovieRepository.findAll()

        return movies.map(MovieDto::from)
    }

    fun findMovieById(id: Long): MovieDto {
        val movie = MovieRepository.findById(id)
            ?: throw IllegalArgumentException(NOT_EXIST_MOVIE_ERROR.format(id))

        return MovieDto.from(movie)
    }

    fun reserve(
        movieId: Long,
        screeningDateTime: LocalDateTime,
        seatPoints: Set<Pair<Int, Int>>,
        movieHouseId: Long = 1
    ): Long {
        val movie = MovieRepository.findById(movieId)
            ?: throw IllegalArgumentException(NOT_EXIST_MOVIE_ERROR.format(movieId))
        val movieHouse = MovieHouseRepository.findById(movieHouseId)
            ?: throw IllegalArgumentException(NOT_EXIST_MOVIE_HOUSE_ERROR.format(movieHouseId))

        val reservationResult = movie.reserve(
            ScreeningInfoOfMovie(screeningDateTime, movieHouse),
            seatPoints.map { Point(it.first, it.second) }.toSet()
        )

        return ReservationResultRepository.save(reservationResult)
    }
}
