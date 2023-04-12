package woowacourse.movie.domain.discountPolicy

import org.assertj.core.api.Assertions
import org.junit.Test
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import java.time.LocalDateTime

class OffTimeTest {
    @Test
    fun `11시 이전일 경우 2000원 할인이 적용된다`() {
        // given
        val offTime = OffTime()
        val date = LocalDateTime.of(2023, 1, 10, 10, 0)
        val price = Price(13000)
        val reservationDetail = ReservationDetail(date, 1, price)

        // when
        val actual = offTime.discount(reservationDetail).price.value

        // then
        val expected = 11000
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `11시 이후, 20시 이전일 경우 2000원 할인이 적용되지 않는다`() {
        // given
        val offTime = OffTime()
        val date = LocalDateTime.of(2023, 1, 10, 11, 0)
        val price = Price(13000)
        val reservationDetail = ReservationDetail(date, 1, price)

        // when
        val actual = offTime.discount(reservationDetail).price.value

        // then
        val expected = 13000
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `20시 이후 일 경우 2000원 할인이 적용된다`() {
        // given
        val offTime = OffTime()
        val date = LocalDateTime.of(2023, 1, 10, 21, 0)
        val price = Price(13000)
        val reservationDetail = ReservationDetail(date, 1, price)

        // when
        val actual = offTime.discount(reservationDetail).price.value

        // then
        val expected = 11000
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
