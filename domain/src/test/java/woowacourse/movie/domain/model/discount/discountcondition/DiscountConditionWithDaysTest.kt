package woowacourse.movie.domain.model.discount.discountcondition

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class DiscountConditionWithDaysTest {

    @ValueSource(ints = [5, 15, 25])
    @ParameterizedTest
    fun `할인 조건이 5, 15, 25일 일때 할인 날짜이면 True 를 반환 한다 `(day: Int) {
        // given
        val discountDays = listOf(5, 15, 25)
        val discountCondition = DiscountConditionWithDays(discountDays)
        val actual = discountCondition.isDiscount(LocalDateTime.of(2023, 10, day, 0, 0))

        // then
        assertThat(actual).isTrue
    }

    @ValueSource(ints = [1, 16, 30])
    @ParameterizedTest
    fun `할인 조건이 5, 15, 25일 일때 할인 날짜가 아니면 False 를 반환 한다 `(day: Int) {
        // given
        val discountDays = listOf(5, 15, 25)
        val discountCondition = DiscountConditionWithDays(discountDays)

        // when
        val actual =
            discountCondition.isDiscount(LocalDateTime.of(2023, 4, day, 0, 0))

        // then
        assertThat(actual).isFalse
    }
}
