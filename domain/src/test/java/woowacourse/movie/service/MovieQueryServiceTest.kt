package woowacourse.movie.service

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MovieQueryServiceTest {

    @Test
    fun `특정 영화의 예매되지 않은 상영과 매핑된 예매를 요청하면 에러가 발생한다`() {
        val movieId = MovieService.save("title", 1, "summary")

        assertThatIllegalArgumentException().isThrownBy {
            MovieQueryService.getReservation(movieId, LocalDateTime.now())
        }.withMessage("해당 상영은 예매되지 않았습니다.")
    }
}
