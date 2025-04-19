package woowacourse.movie.data

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.R
import woowacourse.movie.model.data.Movie
import java.time.LocalDate

class MovieTest {
    @Test
    fun `제목, 상영일, 러닝타임, 포스터 이미지 경로를 가진다`() {
        val movie =
            Movie(
                "승부",
                LocalDate.of(2025, 3, 26),
                LocalDate.of(2025, 3, 27),
                115,
                R.drawable.match,
            )

        assertSoftly(movie) {
            title shouldBe "승부"
            startScreeningDate shouldBe LocalDate.of(2025, 3, 26)
            endScreeningDate shouldBe LocalDate.of(2025, 3, 27)
            runningTime shouldBe 115
            posterRes shouldBe R.drawable.match
        }
    }
}
