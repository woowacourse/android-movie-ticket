package woowacourse.movie.domain.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieRepositoryImpl
import woowacourse.movie.domain.repository.MovieRepository

class MovieRepositoryTest {
    private lateinit var repository: MovieRepository

    @BeforeEach
    fun setup() {
        repository = MovieRepositoryImpl()
    }

    @Test
    fun `loadMovies는 모든 영화 리스트를 반환한다`() {
        val movies = repository.loadMovies()
        assertEquals(1, movies.size)
    }

    @Test
    fun `getMovie는 id가 전달되면 해당하는 Movie가 반환된다`() {
        val movieId = 1
        val movie = repository.getMovie(movieId)
        assertEquals(movieId, movie.movieId)
    }

    @Test
    fun `getMovie는 유효하지 않은 id가 전달되면 default 값이 반환된다`() {
        val invalidMovieId = -1
        val movie = repository.getMovie(invalidMovieId)
        assertEquals("영화가 존재하지 않습니다.", movie.title)
    }
}
