package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MovieRepositoryTest {
    private lateinit var movieRepository: MovieRepository

    @BeforeEach
    fun setUp() {
        movieRepository = MovieRepository()
    }

    @Test
    fun `리포지토리 초기화 시 영화 데이터가 로드되어야 함`() {
        val movies = movieRepository.getMovies()
        assertTrue(movies.isNotEmpty())
    }

    @Test
    fun `getMovies 호출 시 반환된 객체가 List UiMovie 타입이어야 함`() {
        val movies = movieRepository.getMovies()
        assertInstanceOf(UiMovie::class.java, movies[0])
    }

    @Test
    fun `getMovieAt 호출 시 반환된 객체가 UiMovie 타입이어야 함`() {
        val movie = movieRepository.getMovieAt(0)
        assertInstanceOf(UiMovie::class.java, movie)
    }
}
