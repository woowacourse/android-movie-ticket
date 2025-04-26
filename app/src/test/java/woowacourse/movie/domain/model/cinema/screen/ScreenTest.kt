package woowacourse.movie.domain.model.cinema.screen

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ScreenTest {
    @Nested
    inner class `Screen 생성 시` {
        @Test
        fun `row와 col 크기에 맞게 좌석이 생성된다`() {
            val screenSize = ScreenSize(5, 4)
            val screen = Screen(screenSize)

            assertThat(screen.seats.size).isEqualTo(20)
        }

        @Test
        fun `row에 따라 좌석 타입이 올바르게 설정된다`() {
            val screenSize = ScreenSize(5, 4)
            val screen = Screen(screenSize)

            screen.seats.forEach { seat ->
                val expectedSeatType =
                    when (seat.row) {
                        0, 1 -> SeatType.B_CLASS
                        2, 3 -> SeatType.S_CLASS
                        else -> SeatType.A_CLASS
                    }
                assertThat(expectedSeatType).isEqualTo(seat.type)
            }
        }
    }
}
