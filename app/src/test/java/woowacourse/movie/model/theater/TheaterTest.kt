package woowacourse.movie.model.theater

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

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
}
