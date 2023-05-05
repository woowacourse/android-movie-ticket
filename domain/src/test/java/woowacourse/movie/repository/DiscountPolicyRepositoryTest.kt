package woowacourse.movie.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.discount.Money
import java.time.LocalDateTime

internal class DiscountPolicyRepositoryTest {

    @Test
    fun `할인 정책 저장소가 생성되면 무비 데이 할인 정책과 조조 및 심야 할인 정책이 저장된다`() {
        val discountPolicies = DiscountPolicyRepository.findAll()

        val movieDayDiscountPolicy = discountPolicies[0]
        val earlyMorningAndLateNightDiscountPolicy = discountPolicies[1]

        val discountedFeeByMovieDay = movieDayDiscountPolicy.getDiscountedFee(
            LocalDateTime.of(2024, 3, 10, 0, 0),
            Money(10_000)
        )
        val discountedFeeByEarlyMorning = earlyMorningAndLateNightDiscountPolicy.getDiscountedFee(
            LocalDateTime.of(2024, 3, 1, 10, 0),
            Money(10_000)
        )

        assertAll(
            { assertThat(discountedFeeByMovieDay).isEqualTo(Money(9_000)) },
            { assertThat(discountedFeeByEarlyMorning).isEqualTo(Money(8_000)) }
        )
    }
}
