package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieTest {
    @Test
    fun `영화 정보를 생성한다`() {
        val movie = createMovieInfo()

        assertThat(movie.posterImageId).isEqualTo(POSTER_IMAGE_ID)
        assertThat(movie.title).isEqualTo(TITLE)
        assertThat(movie.screeningDate).isEqualTo(SCREENING_DATE)
        assertThat(movie.runningTime).isEqualTo(RUNNING_TIME)
        assertThat(movie.summary).isEqualTo(SUMMARY)
    }
}
