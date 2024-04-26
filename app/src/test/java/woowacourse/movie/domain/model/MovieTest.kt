package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieTest {
    @Test
    fun `영화 정보를 생성한다`() {
        val movie = createMovieInfo()

        assertThat(movie.movieId).isEqualTo(MOVIE_ID)
        assertThat(movie.posterImageId).isEqualTo(POSTER_IMAGE_ID)
        assertThat(movie.title).isEqualTo(TITLE)
        assertThat(movie.screeningInfo).isEqualTo(screeningInfo)
        assertThat(movie.summary).isEqualTo(SUMMARY)
    }
}
