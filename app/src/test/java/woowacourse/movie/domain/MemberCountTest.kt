package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import woowacourse.movie.domain.seat.Seat

class MemberCountTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, -100])
    fun 예매_가능한_인원_수는_1명_이상이다(count: Int) {
        assertThrows<IllegalArgumentException> {
            Seat(count)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1,13000", "2,26000"])
    fun 인원에따라_예매_가격을_계산할_수_있다(count: Int, price: Int) {
        val seat = Seat(count)

        val actual = seat.calculateTicketPrices()

        val expected = price

        assertThat(actual).isEqualTo(expected)
    }
}
