package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoviesTest {
    @Test
    fun `영화 목록을 초기화한다`() {
        val movies = Movies()

        assertThat(movies.movies).isNotEmpty
    }
}
