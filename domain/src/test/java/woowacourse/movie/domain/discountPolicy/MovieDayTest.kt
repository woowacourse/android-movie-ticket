package woowacourse.movie.domain.discountPolicy

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.ReservationDetail
import java.time.LocalDateTime

@RunWith(Enclosed::class)
class MovieDayTest {
    @RunWith(Parameterized::class)
    class ParametrizedTest(private val day: Int) {
        @Test
        fun `무비데이(매월 10,20,30일)인 경우 10% 할인이 적용된다`() {
            // given
            val date = LocalDateTime.of(2023, 1, day, 0, 0)
            val price = Price(13000)
            val reservationDetail = ReservationDetail(date, 1)

            // when
            val actual = MovieDay.discount(reservationDetail, price).value

            // then
            val expected = 11700
            assertEquals(actual, expected)
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = listOf(
                10, 20, 30
            )
        }
    }

    class NotParameterizedTest {
        @Test
        fun `무비데이(매월 10,20,30일)가 아닌 경우 10% 할인이 적용되지 않는다`() {
            // given
            val date = LocalDateTime.of(2023, 1, 11, 0, 0)
            val price = Price(13000)
            val reservationDetail = ReservationDetail(date, 1)

            // when
            val actual = MovieDay.discount(reservationDetail, price).value

            // then
            val expected = 13000
            assertEquals(actual, expected)
        }
    }
}
