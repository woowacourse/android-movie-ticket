package woowacourse.movie.domain.discount

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class MovieDayDiscountTest {
    @Test
    fun `기존 금액이 26000원일 때 날짜가 10일(무비데이)인 경우 예매 금액은 23400원이다`() {
        val actual = MovieDayDiscount(
            LocalDateTime.of(2023, 8, 10, 15, 0)
        ).getDiscountPrice(26000)

        assertEquals(23400, actual)
    }

    @Test
    fun `기존 금액이 26000원일 때 날짜가 무비데이가 아닌 경우 예매 금액은 26000원이다`() {
        val actual = MovieDayDiscount(
            LocalDateTime.of(2023, 8, 11, 15, 0)
        ).getDiscountPrice(26000)

        assertEquals(26000, actual)
    }
}
