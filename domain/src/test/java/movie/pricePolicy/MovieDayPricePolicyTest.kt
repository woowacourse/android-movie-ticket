package movie.pricePolicy

import data.PricePolicyInfo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime

class MovieDayPricePolicyTest {
    @ParameterizedTest
    @ValueSource(ints = [10, 20, 30])
    fun `달마다 10의 배수인 요일에 할인 된다`(day: Int) {
        // given
        val movieDayPricePolicy = MovieDayPricePolicy(0.9)
        val localDateTime = LocalDateTime.of(2021, 10, day, 10, 0)
        val pricePolicyInfo = PricePolicyInfo(10000, localDateTime)

        // when
        val price = movieDayPricePolicy.calculatePrice(pricePolicyInfo)

        // then
        assertThat(price.price).isEqualTo(9000)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31])
    fun `달마다 10의 배수인 요일이 아니면 할인이 되지 않는다`(day: Int) {
        // given
        val movieDayPricePolicy = MovieDayPricePolicy(0.9)
        val localDateTime = LocalDateTime.of(2021, 10, day, 10, 0)
        val pricePolicyInfo = PricePolicyInfo(10000, localDateTime)

        // when
        val price = movieDayPricePolicy.calculatePrice(pricePolicyInfo)

        // then
        assertThat(price.price).isEqualTo(10000)
    }
}
