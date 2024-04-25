package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TheaterTest {
    @Test
    fun `상영관_정보_생성_시_올바른_상영관_크기_정보와_등급별_열의_수를_보유한다`() {
        // given
        val theater = Theater.of(2, 1, 2, 4)
        // then
        assertAll(
            { assertEquals(TheaterSize(5, 4), theater.theaterSize) },
            { assertEquals(2, theater.sizesOfRows[SeatClass.S]) },
            { assertEquals(1, theater.sizesOfRows[SeatClass.A]) },
            { assertEquals(2, theater.sizesOfRows[SeatClass.B]) },
        )
    }
}
