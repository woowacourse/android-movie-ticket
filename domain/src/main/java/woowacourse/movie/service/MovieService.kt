package woowacourse.movie.service

import woowacourse.movie.dto.MovieDto
import woowacourse.movie.repository.MovieRepository

object MovieService {

    fun findAllMovies(): List<MovieDto> {
        val movies = MovieRepository.findAll()

        return movies.map(MovieDto::from)
    }
}
