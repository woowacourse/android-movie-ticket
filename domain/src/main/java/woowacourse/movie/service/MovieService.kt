package woowacourse.movie.service

import woowacourse.movie.dto.MovieDto
import woowacourse.movie.repository.MovieRepository

object MovieService {

    private const val NOT_EXIST_MOVIE_ERROR = "아이디가 %d인 영화가 존재하지 않습니다."

    fun findAllMovies(): List<MovieDto> {
        val movies = MovieRepository.findAll()

        return movies.map(MovieDto::from)
    }

    fun findMovieById(id: Long): MovieDto {
        val movie = MovieRepository.findById(id)
            ?: throw IllegalArgumentException(NOT_EXIST_MOVIE_ERROR.format(id))

        return MovieDto.from(movie)
    }
}
