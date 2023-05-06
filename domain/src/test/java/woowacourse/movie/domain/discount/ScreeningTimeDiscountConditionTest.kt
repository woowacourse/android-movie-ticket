package woowacourse.movie.domain.discount

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeDiscountConditionTest {

    @Test
    fun `만약 예매 시간이 할인 시간에 포함된다면 할인 조건을 만족한다`() {
        val screeningTimeDiscountCondition =
            ScreeningTimeDiscountCondition(
                listOf(
                    TimeRange(
                        LocalTime.MIN,
                        LocalTime.NOON
                    )
                )
            )
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 9, 0)

        assertThat(screeningTimeDiscountCondition.isSatisfiedBy(screeningDateTime)).isTrue
    }

    @Test
    fun `만약 예매 시간이 할인 시간에 포함되지 않는다면 할인 조건을 만족하지 않는다`() {
        val screeningTimeDiscountCondition =
            ScreeningTimeDiscountCondition(
                listOf(
                    TimeRange(
                        LocalTime.MIN,
                        LocalTime.NOON
                    )
                )
            )
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 13, 0)

        assertThat(screeningTimeDiscountCondition.isSatisfiedBy(screeningDateTime)).isFalse
    }
}
