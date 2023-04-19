package domain.discountPolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MovieDayTest {

    @Test
    fun `무비데이(매월 10,20,30일)인 경우 10% 할인이 적용된다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 10, 0, 0)
        val price = domain.Price(13000)
        val ticket = domain.Ticket(
            date,
            1,
            DisCountPolicies(
                listOf(
                   MovieDay(),
                    OffTime()
                )
            )
        )

        // when
        val actual = MovieDay().discount(ticket, domain.Price(13000)).value

        // then
        val expected = 11700
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무비데이(매월 10,20,30일)가 아닌 경우 10% 할인이 적용되지 않는다`() {
        // given
        val date = LocalDateTime.of(2023, 1, 11, 0, 0)
        val price = domain.Price(13000)
        val ticket = domain.Ticket(
            date,
            1,
            DisCountPolicies(
                listOf(
                   MovieDay(),
                    OffTime()
                )
            )
        )

        // when
        val actual = MovieDay().discount(ticket, domain.Price()).value

        // then
        val expected = 13000
        assertThat(actual).isEqualTo(expected)
    }
}
