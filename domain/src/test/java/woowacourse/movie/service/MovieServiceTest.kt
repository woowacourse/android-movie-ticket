package woowacourse.movie.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import woowacourse.movie.repository.MovieRepository


class MovieServiceTest {

    @Test
    fun `영화 제목과 러닝 타임과 요약을 이용해 영화를 저장할 수 있다`() {
        val title = "해리 포터"
        val runningTime = 152
        val summary = "요약"

        val movieId = MovieService.save(title, runningTime, summary)

        val movie = MovieRepository.findById(movieId)
        assertAll(
            { assertThat(movie.title).isEqualTo(title) },
            { assertThat(movie.runningTime.value).isEqualTo(runningTime) },
            { assertThat(movie.summary).isEqualTo(summary) }
        )
    }
}
