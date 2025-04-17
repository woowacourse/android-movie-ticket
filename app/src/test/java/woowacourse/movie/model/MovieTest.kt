package woowacourse.movie.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class MovieTest {
    @Test
    fun `영화 제목은 비어있을 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Movie(
                0,
                "",
                LocalDate.of(2025, 4, 17),
                LocalDate.of(2025, 4, 18),
                152,
            )
        }

        assertDoesNotThrow {
            Movie(
                0,
                "해리포터",
                LocalDate.of(2025, 4, 17),
                LocalDate.of(2025, 4, 18),
                152,
            )
        }
    }

    @Test
    fun `영화 상영시간은 0보다 커야한다`() {
        assertThrows<IllegalArgumentException> {
            Movie(
                0,
                "해리포터",
                LocalDate.of(2025, 4, 17),
                LocalDate.of(2025, 4, 18),
                0,
            )
        }

        assertDoesNotThrow {
            Movie(
                0,
                "해리포터",
                LocalDate.of(2025, 4, 17),
                LocalDate.of(2025, 4, 18),
                152,
            )
        }
    }

    @Test
    fun `영화 시작 날짜는 종료보다 앞서야 한다`() {
        assertThrows<IllegalArgumentException> {
            Movie(
                0,
                "해리포터",
                LocalDate.of(2025, 4, 20),
                LocalDate.of(2025, 4, 18),
                1,
            )
        }

        assertDoesNotThrow {
            Movie(
                0,
                "해리포터",
                LocalDate.of(2025, 4, 17),
                LocalDate.of(2025, 4, 18),
                152,
            )
        }
    }
}
