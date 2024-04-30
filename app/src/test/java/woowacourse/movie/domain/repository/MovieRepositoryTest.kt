package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.FakeImage
import woowacourse.movie.domain.model.Movie

class MovieRepositoryTest {
    private val movieRepository2 = FakeMovieRepository()

    @Test
    fun `영화 id 로 영화 포스터를 찾는다`() {
        val actual = movieRepository2.imageSrc(1)
        assertThat(actual).isEqualTo(FakeImage("1"))
    }

    @Test
    fun `영화 id 로 영화를 찾는다`() {
        val actual = movieRepository2.findById(1)
        val expected =
            Movie(
                1,
                "title1",
                1,
                "description1",
            )
        assertThat(actual).isEqualTo(expected)
    }
}
