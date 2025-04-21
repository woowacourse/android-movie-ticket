package woowacourse.movie.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.LocalDateTime

class TicketText {
    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `티켓의 개수가 1보다 작을 경우 오류가 발생한다`(value: Int) {
        val movie =
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = LocalDate.of(2025, 4, 16),
                endDate = LocalDate.of(2025, 4, 25),
                runningTime = 152,
            )
        assertThrows<IllegalArgumentException> {
            Ticket(
                movie,
                LocalDateTime.of(2025, 4, 16, 0, 0),
                value,
            )
        }
    }
}
