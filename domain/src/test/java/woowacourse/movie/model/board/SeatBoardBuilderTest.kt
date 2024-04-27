package woowacourse.movie.model.board

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import woowacourse.movie.fixtures.bannedPositions
import woowacourse.movie.fixtures.bannedSeats
import woowacourse.movie.fixtures.reserveSeats
import woowacourse.movie.fixtures.reservedSeatPositions
import woowacourse.movie.fixtures.totalPrice
import woowacourse.movie.model.Price

class SeatBoardBuilderTest {
    @ParameterizedTest
    @CsvSource(value = ["4,4", "5,3", "5,4"])
    fun `예약 좌석이 Board 범위를 벗어 나면 예외`(
        x: Int,
        y: Int,
    ) {
        shouldThrow<IllegalArgumentException> {
            buildSeatBoard {
                size(width = 5, height = 4)
                reservedSeatPositions(Position(x, y))
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["4,4", "5,3", "5,4"])
    fun `금지 좌석이 Board 범위를 벗어 나면 예외`(
        x: Int,
        y: Int,
    ) {
        shouldThrow<IllegalArgumentException> {
            buildSeatBoard {
                size(width = 5, height = 4)
                bannedPositions(Position(x, y))
            }
        }
    }

    @Test
    fun `예약 좌석과 금지 좌석의 좌석이 중복되면 예외`() {
        // given
        val position = Position(3, 3)
        // when & then
        shouldThrow<IllegalArgumentException> {
            buildSeatBoard {
                reservedSeatPositions(position)
                bannedPositions(position)
            }
        }
    }

    @Test
    fun `예약 좌석과 금지 좌석의 좌석 위치가 중복되지 않으면, 예외가 발생하지 않는다`() {
        // given
        val reservedSeatPosition = Position(3, 3)
        val bannedPosition = Position(4, 3)
        // when & then
        shouldNotThrow<IllegalArgumentException> {
            buildSeatBoard {
                reservedSeatPositions(reservedSeatPosition)
                bannedPositions(bannedPosition)
            }
        }
    }

    @Test
    fun `예약된 좌석을 추가할 수 있다`() {
        // given
        val positions = setOf(Position(3, 3), Position(2, 3))
        val expectSize = 2
        // when
        val board =
            buildSeatBoard {
                reservedSeatPositions(positions)
            }
        val reservedSeats = board.reserveSeats()
        // then
        reservedSeats.size shouldBe expectSize
    }

    @Test
    fun `금지된 좌석을 추가할 수 있다`() {
        // given
        val positions = setOf(Position(3, 3), Position(2, 3))
        val expectSize = 2
        // when
        val board =
            buildSeatBoard {
                bannedPositions(positions)
            }
        val bannedPosition = board.bannedSeats()
        // then
        bannedPosition.size shouldBe expectSize
    }

    @Test
    fun `모든 좌석의 가격을 10_000원으로 설정할 수 있다`() {
        // given
        val seatPrice = Price(10_000)
        val width = 3
        val height = 3
        val expectTotalPrice = 90_000
        // when
        val pricePolicy = SeatPricePolicy { seatPrice }
        val board =
            buildSeatBoard {
                size(width, height)
                pricePolicy(pricePolicy)
            }
        val totalPrice = board.totalPrice()
        // then
        totalPrice shouldBe expectTotalPrice
    }
}
