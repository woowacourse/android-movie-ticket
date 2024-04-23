package woowacourse.movie.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MockMovieRepositoryTest {
    @Test
    fun `존재하는 모든 영화를 가져올 수 있다`() {
        val movies = MockMovieRepository.loadMovies()
        val result = movies?.size
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `id를 key로 영화를 불러올 수 있다`() {
        val movie = MockMovieRepository.findMovieById(0)
        val result = movie?.id
        assertThat(result).isEqualTo(0)
    }
}
