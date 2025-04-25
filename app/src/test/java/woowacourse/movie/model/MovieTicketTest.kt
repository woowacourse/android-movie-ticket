package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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
                count = 4,
            )

        // when
        val price = ticket.price()

        // then
        assertThat(price).isEqualTo(52000)
    }
}
