package woowacourse.movie.repository

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class MovieRepositoryTest {

    @Test
    fun `영화 저장소가 생성되면 샘플 영화 4개가 추가된다`() {
        val movies = MovieRepository.findAll()

        assertThat(movies).hasSize(4)
    }
}
