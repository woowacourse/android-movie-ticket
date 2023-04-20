package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class ReservationResultTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 201])
    fun `예매 인원이 1명 이상 200명 이하가 아니면 에러가 발생한다`(illegalPeopleCount: Int) {
        assertThatIllegalArgumentException().isThrownBy {
            ReservationResult(LocalDateTime.now(), illegalPeopleCount)
        }.withMessage("[ERROR] 예매 인원은 최소 1명 이상 최대 200명 이하여야 합니다.")
    }

    @Test
    fun `예매 금액은 영화 티켓 가격에 할인 정책이 적용된 금액 곱하기 예매 인원 수이다`() {
        val audienceCount = 4
        val reservationResult = ReservationResult(
            LocalDateTime.of(3021, 3, 10, 9, 0),
            audienceCount
        )

        val actual = reservationResult.fee

        val expected = Money(38_800)
        assertThat(actual).isEqualTo(expected)
    }
}
