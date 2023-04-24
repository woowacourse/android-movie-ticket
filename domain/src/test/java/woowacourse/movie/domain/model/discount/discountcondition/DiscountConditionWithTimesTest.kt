package woowacourse.movie.domain.model.discount.discountcondition

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class DiscountConditionWithTimesTest {

    @ValueSource(ints = [9, 10, 11])
    @ParameterizedTest
    fun `할인 조건이 9, 10, 11시 일때 할인 시간이면 True 를 반환 한다 `(hour: Int) {
        // given
        val discountHours = listOf(9, 10, 11)
        val discountCondition = DiscountConditionWithTimes(discountHours)

        // when
        val actual = discountCondition.isDiscount(LocalDateTime.of(2023, 4, 1, hour, 0))

        // then
        Assertions.assertThat(actual).isTrue
    }

    @ValueSource(ints = [1, 8, 12, 23])
    @ParameterizedTest
    fun `할인 조건이 9, 10, 11시 일때 할인 시간이 아니면 False 를 반환 한다 `(hour: Int) {
        // given
        val discountHours = listOf(9, 10, 11)
        val discountCondition = DiscountConditionWithTimes(discountHours)

        // when
        val actual =
            discountCondition.isDiscount(LocalDateTime.of(2023, 4, 1, hour, 0))

        // then
        Assertions.assertThat(actual).isFalse
    }
}
