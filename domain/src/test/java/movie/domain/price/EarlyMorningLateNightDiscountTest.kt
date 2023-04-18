package movie.domain.price

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EarlyMorningLateNightDiscountTest {

    @Test
    fun `할인전 금액이 13000원인 경우 조조,심야할인을 적용하면 11000원을 반환한다`() {
        assertThat(EarlyMorningLateNightDiscount().discount(13000)).isEqualTo(11000)
    }
}
