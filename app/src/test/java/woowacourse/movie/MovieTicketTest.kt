package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieTicket

class MovieTicketTest {
    @Test
    fun `티켓 개수를 받으면 총 결제 금액을 반환한다`() {
        // given
        val ticket = MovieTicket("라라랜드", "2025.04.17 12:53", 4)

        // when
        val price = ticket.price()

        // then
        assertThat(price).isEqualTo(52000)
    }
}
