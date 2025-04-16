package woowacourse.movie.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Movies
import woowacourse.movie.fixture.MovieFixture

class MovieDaoTest {
    private lateinit var movieDao: MovieDao

    @BeforeEach
    fun setUp() {
        movieDao = MovieDao()
    }

    @Test
    fun ` movie_ticket의 데이터를 불러올 수 있다`() {
        val actual = movieDao.movies()
        val expected = Movies(
            mapOf(
                "해리포터와 마법사의 돌" to MovieFixture.movie
            )
        )
        assertThat(actual).isEqualTo(expected)
    }
}
