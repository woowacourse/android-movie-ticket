package woowacourse.movie.db.movie

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.TestFixture.FIRST_MOVIE_ITEM_POSITION
import woowacourse.movie.db.screening.ScreeningDao
import woowacourse.movie.db.screening.ScreeningDatabase
import woowacourse.movie.model.movie.Movie

@RunWith(AndroidJUnit4::class)
class ScreeningDaoTest {
    private val dao = ScreeningDao()
    private val movies = ScreeningDatabase.movies

    @Test
    fun `영화_데이터베이스의_첫번째_영화_데이터를_가져온다`() {
        val actual: Movie = dao.find(FIRST_MOVIE_ITEM_POSITION)
        val expected: Movie = movies[FIRST_MOVIE_ITEM_POSITION]
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화_데이터베이스의_모든_데이터를_가져온다`() {
        val actual = dao.findAll().size
        val expected = movies.size
        actual shouldBe expected
    }
}
