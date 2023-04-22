package woowacourse.movie.domain.discount

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.PeopleCount
import java.time.LocalDateTime

class LateTimeDiscountTest {
    @Test
    fun `기존 금액이 26000원일 때 시간이 늦은 경우 (20시 이후) 예매 금액은 22000원이다`() {
        val actual = LateTimeDiscount(
            LocalDateTime.of(2023, 8, 7, 22, 0),
            PeopleCount(2)
        ).getDiscountPrice(26000)

        assertEquals(22000, actual)
    }

    @Test
    fun `기존 금액이 26000원일 때 시간이 늦지 않은 경우 예매 금액은 26000원이다`() {
        val actual = LateTimeDiscount(
            LocalDateTime.of(2023, 8, 7, 15, 0),
            PeopleCount(2)
        ).getDiscountPrice(26000)

        assertEquals(26000, actual)
    }
}
