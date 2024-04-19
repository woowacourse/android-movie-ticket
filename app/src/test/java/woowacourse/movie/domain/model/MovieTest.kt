package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieTest {
    @Test
    fun `영화 정보를 생성한다`() {
        val movie =
            Movie(
                posterImageId = 1,
                title = "테넷",
                screeningDate = Date.from("2020.08.26"),
                runningTime = 150,
                summary = "시간을 건너뛰는 SF 영화",
            )

        assertThat(movie.posterImageId).isEqualTo(1)
        assertThat(movie.title).isEqualTo("테넷")
        assertThat(movie.screeningDate).isEqualTo("2020-08-26")
        assertThat(movie.runningTime).isEqualTo(150)
        assertThat(movie.summary).isEqualTo("시간을 건너뛰는 SF 영화")
    }
}
