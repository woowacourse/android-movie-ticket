package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Screen
import java.time.LocalDate
import java.time.LocalDateTime

class TicketTest {
    @Test
    fun `Ticket의 count 초기 값은 1 이다`() {
        val ticket = Ticket(MOCK_SCREEN.id)
        assertThat(ticket.count).isEqualTo(1)
    }

    @Test
    fun `addCount 함수를 호출하면 count의 값은 1 증가한다 `() {
        // given
        val ticket = Ticket(MOCK_SCREEN.id)

        // when
        ticket.addCount()

        // then
        assertThat(ticket.count).isEqualTo(2)
    }

    @Test
    fun `count의 값이 2이상일 때, sub함수 호출하면 count의 값이 1감소한다`() {
        // given
        val ticket = Ticket(MOCK_SCREEN.id)
        ticket.addCount()
        val count = ticket.count

        // when
        ticket.subCount()

        // then
        assertThat(ticket.count).isEqualTo(count - 1)
    }

    @Test
    fun `count의 값이 1이하일 때, sub함수 호출하면 count의 값은 감소하지 않는다`() {
        val ticket = Ticket(MOCK_SCREEN.id)

        ticket.subCount()

        assertThat(ticket.count).isEqualTo(1)
    }

    companion object {
        private val MOCK_MOVIE =
            Movie(
                id = 1,
                title = "mockTitle",
                runningTime = 100,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "mock description",
                imgResId = 0,
            )

        private val MOCK_SCREEN =
            Screen.from(
                movie = MOCK_MOVIE,
            )
    }
}
