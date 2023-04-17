package movie.pricePolicy

import data.PricePolicyInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class EveningPricePolicyTest {
    @Test
    fun `야간 시간 이후 할인이 된다`() {
        // given
        val eveningPricePolicy = EveningPricePolicy(1000, 10)
        val localDateTime = LocalDateTime.of(2021, 1, 1, 10, 0)
        val pricePolicyInfo = PricePolicyInfo(10000, localDateTime)

        // when
        val price = eveningPricePolicy.calculatePrice(pricePolicyInfo)

        // then
        assertThat(price.price).isEqualTo(9000)
    }

    @Test
    fun `야간 시간 이전 이면 할인이 되지 않는다`() {
        // given
        val eveningPricePolicy = EveningPricePolicy(1000, 10)
        val localDateTime = LocalDateTime.of(2021, 1, 1, 9, 0)
        val pricePolicyInfo = PricePolicyInfo(10000, localDateTime)

        // when
        val price = eveningPricePolicy.calculatePrice(pricePolicyInfo)

        // then
        assertThat(price.price).isEqualTo(10000)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 23])
    fun `시간 범위는 0부터 23까지다`(hour: Int) {
        assertDoesNotThrow {
            EveningPricePolicy(1000, hour)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 24])
    fun `시간 범위는 0부터 23까지다_예외`(hour: Int) {
        assertThrows<IllegalArgumentException> {
            EveningPricePolicy(1000, hour)
        }
    }
}
