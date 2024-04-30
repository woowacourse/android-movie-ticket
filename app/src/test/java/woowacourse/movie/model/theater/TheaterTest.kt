package woowacourse.movie.model.theater

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class TheaterTest {
    @Test
    fun `상영관_정보_생성_시_올바른_상영관_크기_정보와_등급별_열의_수를_보유한다`() {
        // given
        val theater =
            Theater.of(
                mapOf(
                    1 to SeatClass.B,
                    2 to SeatClass.B,
                    3 to SeatClass.S,
                    4 to SeatClass.S,
                    5 to SeatClass.A,
                ),
                4,
            )
        // then
        assertAll(
            { assertEquals(TheaterSize(5, 4), theater.theaterSize) },
            { assertEquals(SeatClass.B, theater.rowClassInfo[1]) },
            { assertEquals(SeatClass.S, theater.rowClassInfo[3]) },
            { assertEquals(SeatClass.A, theater.rowClassInfo[5]) },
        )
    }

    @Test
    fun `상영관_정보_생성_시_중복된_행_번호_제공_시_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("중복된 행 번호를 가질 수 없습니다.") {
            Theater.of(
                mapOf(
                    1 to SeatClass.B,
                    1 to SeatClass.B,
                    3 to SeatClass.S,
                    4 to SeatClass.S,
                    5 to SeatClass.A,
                ),
                4,
            )
        }
    }

    @Test
    fun `상영관_정보_생성_시_첫_행_번호부터_연속되지_않은_행_번호_제공_시_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException>("중복된 행 번호를 가질 수 없습니다.") {
            Theater.of(
                mapOf(
                    2 to SeatClass.B,
                    3 to SeatClass.B,
                    4 to SeatClass.S,
                    5 to SeatClass.S,
                    6 to SeatClass.A,
                ),
                4,
            )
        }
    }
}
