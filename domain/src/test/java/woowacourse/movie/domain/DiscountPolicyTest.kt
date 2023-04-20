package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DiscountPolicyTest {

    @Test
    fun `무비 데이이고 조조나 야간이 아닐 때 예매하면 영화 요금을 10퍼센트 할인 받는다`() {
        val screeningDateTime = LocalDateTime.of(2021, 3, 10, 15, 0)
        val movieFee = Money(13_000)

        val actual = DiscountPolicy.getDiscountedFee(screeningDateTime, movieFee)

        val expected = Money(11_700)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비 데이가 아니고 조조일 때 예매하면 영화 요금을 2000원 할인 받는다`() {
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 10, 0)
        val movieFee = Money(13_000)

        val actual = DiscountPolicy.getDiscountedFee(screeningDateTime, movieFee)

        val expected = Money(11_000)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비 데이가 아니고 야간일 때 예매하면 영화 요금을 2000원 할인을 받는다`() {
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 20, 0)
        val movieFee = Money(13_000)

        val actual = DiscountPolicy.getDiscountedFee(screeningDateTime, movieFee)

        val expected = Money(11_000)
        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun `무비 데이 할인과 상영 시간 할인을 둘 다 받으면 무비 데이 할인을 먼저 적용된다`() {
        val screeningDateTime = LocalDateTime.of(2021, 3, 10, 20, 0)
        val movieFee = Money(13_000)

        val actual = DiscountPolicy.getDiscountedFee(screeningDateTime, movieFee)

        val expected = Money(9700)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `어떤 할인 조건도 만족하지 않는다면 할인된 금액은 초기 예매 금액과 같다`() {
        val screeningDateTime = LocalDateTime.of(2021, 3, 1, 15, 0)
        val movieFee = Money(13_000)

        val actual = DiscountPolicy.getDiscountedFee(screeningDateTime, movieFee)

        assertThat(actual).isEqualTo(movieFee)
    }
}
