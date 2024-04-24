package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.presentation.utils.getDummyReservation

class ReservationTest {
    @Test
    fun `예매 티켓이 세 장이고, 영화 가격이 13,000 일 때 총 가격은 39,000 이다`() {
        // given & when
        val reservation = getDummyReservation()

        // then
        assertThat(reservation.totalPrice).isEqualTo(39_000)
    }
}
