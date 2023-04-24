package woowacourse.movie

import com.woowacourse.domain.seat.SeatTier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatTierTest {

    @ParameterizedTest
    @CsvSource("B, 10000", "A, 12000", "S, 15000")
    fun `각 좌석 등급 마다 가격을 가진다`(seatTier: SeatTier, price: Int) {
        val actual = seatTier.price
        assertThat(actual).isEqualTo(price)
    }

    @ParameterizedTest
    @CsvSource("0, B", "1, B", "2, S", "3, S", "4, A")
    fun `row의 값에 따라 등급을 가진다`(row: Int, seatTier: SeatTier) {
        val actual = SeatTier.getByRow(row)
        assertThat(actual).isEqualTo(seatTier)
    }
}
