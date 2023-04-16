package movie.domain.price

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieDayDiscountTest {

    @Test
    fun `할인전 금액이 13000원인 경우 무비데이할인을 적용하면 11700원을 반환한다`() {
        assertThat(MovieDayDiscount().discount(13000)).isEqualTo(11700)
    }
}
