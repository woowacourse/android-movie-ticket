package woowacourse.movie.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    private val movieDao = MovieDao()

    @Test
    fun `movieId로_데이터베이스에_저장된_영화를_조회할_수_있다`() {
        // given
        val movieId = 1

        // when
        val movie = movieDao.find(movieId)

        // then
        assertThat(movie.id).isEqualTo(1)
    }

    @Test
    fun `데이터베이스에_저장된_영화를_모든_데이터를_조회할_수_있다`() {
        // given,when
        val movies = movieDao.findAll()

        // then
        assertThat(movies.size).isEqualTo(2)
    }
}
