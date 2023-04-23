package woowacourse.movie.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class MovieServiceTest {

    @Test
    fun `아이디로 영화를 조회할 때 그 아이디로 저장된 영화가 존재하지 않는다면 에러가 발생한다`() {
        val notExistMovieId = 100L

        assertThatIllegalArgumentException().isThrownBy {
            MovieService.findMovieById(notExistMovieId)
        }.withMessage("아이디가 ${notExistMovieId}인 영화가 존재하지 않습니다.")
    }

    @Test
    fun `어떤 영화를 예매하면 예매 결과의 아이디를 반환한다`() {
        val reservationResultId =
            MovieService.reserve(1, LocalDateTime.of(2024, 3, 1, 10, 0), setOf(1 to 1))

        assertThat(reservationResultId).isEqualTo(1)
    }
}
