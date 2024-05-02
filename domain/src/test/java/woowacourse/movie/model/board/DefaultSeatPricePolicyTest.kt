package woowacourse.movie.model.board

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.model.Price

class DefaultSeatPricePolicyTest {
    @Test
    fun `S 등급의 좌석은 15_000원 이다`() {
        // given
        val grade = SeatGrade.S
        val expect = Price(15_000)
        // when
        val actual = DefaultSeatPricePolicy().price(grade)
        // then
        actual shouldBe expect
    }

    @Test
    fun `A 등급의 좌석은 12_000원 이다`() {
        // given
        val grade = SeatGrade.A
        val expect = Price(12_000)
        // when
        val actual = DefaultSeatPricePolicy().price(grade)
        // then
        actual shouldBe expect
    }

    @Test
    fun `B 등급의 좌석은 10_000원 이다`() {
        // given
        val grade = SeatGrade.B
        val expect = Price(10_000)
        // when
        val actual = DefaultSeatPricePolicy().price(grade)
        // then
        actual shouldBe expect
    }
}
