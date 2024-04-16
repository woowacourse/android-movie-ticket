package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieTest {
    @Test
    fun `영화 정보를 생성한다`() {
        val movie =
            Movie(
                posterImage = "posterImage",
                title = "테넷",
                screeningDate = "2020-08-26",
                runningTime = 150,
                summary = "시간을 건너뛰는 SF 영화",
            )

        assertThat(movie.posterImage).isEqualTo("posterImage")
        assertThat(movie.title).isEqualTo("테넷")
        assertThat(movie.screeningDate).isEqualTo("2020-08-26")
        assertThat(movie.runningTime).isEqualTo(150)
        assertThat(movie.summary).isEqualTo("시간을 건너뛰는 SF 영화")
    }
}
