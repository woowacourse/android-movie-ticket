package woowacourse.movie.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieDaoTest {
    private val movieDao = MovieDao()

    @Test
    fun `movieId로 data에 저장된 영화를 조회할 수 있다`() {
        // given
        val movieId = 1

        // when
        val movie = movieDao.find(movieId)

        // then
        assertThat(movie.id).isEqualTo(1)
    }

    @Test
    fun `data에 저장된 영화를 모두 조회할 수 있다`() {
        // given,when
        val movies = movieDao.findAll()

        // then
        assertThat(movies.size).isEqualTo(2)
    }
}
