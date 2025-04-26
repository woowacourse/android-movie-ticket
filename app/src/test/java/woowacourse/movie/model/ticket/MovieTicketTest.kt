package woowacourse.movie.model.ticket

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import woowacourse.movie.model.movie.MovieTime
import java.time.LocalDate

class MovieTicketTest {
    @Test
    fun `티켓 개수를 받으면 총 결제 금액을 반환한다`() {
        // given
        val ticket =
            MovieTicket(
                title = "라라랜드",
                movieDate = LocalDate.of(2025, 4, 22),
                movieTime = MovieTime(),
                count = TicketCount(4),
            )

        // when
        val price = ticket.price()

        // then
        Assertions.assertThat(price).isEqualTo(52000)
    }
}
