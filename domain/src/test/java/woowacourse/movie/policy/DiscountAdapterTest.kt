package woowacourse.movie.policy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class DiscountAdapterTest {
    @CsvSource(value = ["10,10,9700", "10,22,9700"])
    @ParameterizedTest
    fun `무비데이면서 조조나 야간이면 티켓이 10퍼센트 할인되고 2000원 할인되어 9700원이다`(day: Int, hour: Int, expected: Int) {
        // given
        val adapter = DiscountAdapter()
        val dateTime = LocalDateTime.of(2024, 1, day, hour, 0)

        // when
        val actual = adapter.getPayment(dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [10, 20, 30])
    @ParameterizedTest
    fun `무비데이면서 할인 시간이 아니면 티켓이 10퍼센트 할인돼 11700원이 된다`(day: Int) {
        // given
        val adapter = DiscountAdapter()
        val dateTime = LocalDateTime.of(2024, 3, day, 17, 0)
        val expected = 11_700

        // when
        val actual = adapter.getPayment(dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ValueSource(ints = [9, 10, 11, 20, 21, 22, 23])
    @ParameterizedTest
    fun `할인 시간이면서 무비데이가 아니면 2000원 할인돼 11000원이 된다`(hour: Int) {
        // given
        val adapter = DiscountAdapter()
        val dateTime = LocalDateTime.of(2024, 3, 1, hour, 0)
        val expected = 11_000

        // when
        val actual = adapter.getPayment(dateTime)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
