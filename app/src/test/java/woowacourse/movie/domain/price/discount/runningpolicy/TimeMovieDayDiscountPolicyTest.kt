package woowacourse.movie.domain.price.discount.runningpolicy

import org.junit.Assert.*
import org.junit.Test
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.price.discount.partialpolicy.EarlyMorningLateNightDiscount
import woowacourse.movie.domain.price.discount.partialpolicy.MovieDayDiscount
import java.time.LocalDate
import java.time.LocalDateTime

class TimeMovieDayDiscountPolicyTest {
    @Test
    fun `11시이전(조조할인)만 적용된다면 시간할인 정책만 담긴 리스트를 반환한다`() {
        val policies = TimeMovieDayDiscountPolicy(
            ScreeningDateTime(
                LocalDateTime.parse("2023-04-01T09:00"),
                ScreeningPeriod(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-30"))
            )
        )

        assertEquals(
            policies.getDiscountPolicies().map { it.javaClass },
            listOf(EarlyMorningLateNightDiscount()).map { it.javaClass }
        )
    }

    @Test
    fun `10일무비데이 할인만 적용된다면 무비데이 할인 정책만 담긴 리스트를 반환한다`() {
        val policies = TimeMovieDayDiscountPolicy(
            ScreeningDateTime(
                LocalDateTime.parse("2023-04-10T16:00"),
                ScreeningPeriod(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-30"))
            )
        )

        assertEquals(
            policies.getDiscountPolicies().map { it.javaClass },
            listOf(MovieDayDiscount()).map { it.javaClass }
        )
    }

    @Test
    fun `10일무비데이 할인과 11시이전(조조할인)이 적용된다면 무비데이 할인 과 시간할인이 담긴 리스트를 반환한다`() {
        val policies = TimeMovieDayDiscountPolicy(
            ScreeningDateTime(
                LocalDateTime.parse("2023-04-10T09:00"),
                ScreeningPeriod(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-30"))
            )
        )

        assertEquals(
            policies.getDiscountPolicies().map { it.javaClass },
            listOf(MovieDayDiscount(), EarlyMorningLateNightDiscount()).map { it.javaClass }
        )
    }
}
