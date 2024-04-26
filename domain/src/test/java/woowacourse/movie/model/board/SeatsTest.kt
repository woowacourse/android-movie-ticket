package woowacourse.movie.model.board

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import woowacourse.movie.fixtures.seat

class SeatsTest {
    @Test
    fun `동일한 position 에 좌석이 있으면 예외 발생`() {
        shouldThrow<IllegalArgumentException> {
            Seats(
                seat(1, 2),
                seat(1, 2)
            )
        }
    }

    @Test
    fun `서로 다른 position 에 좌석이 있다`() {
        shouldNotThrow<IllegalArgumentException> {
            Seats(
                seat(1, 1),
                seat(1, 2)
            )
        }
    }

    @Test
    fun `좌석들의 총 금액을 구할 수 있다`() {
        // given
        val seats = Seats(
            seat(1, 1, price = 1_000),
            seat(1, 2, price = 1_000),
        )
        val expect = 2_000
        // when
        val actual = seats.totalPrice()
        // then
        actual shouldBe expect
    }
}
