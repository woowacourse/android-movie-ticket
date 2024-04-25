package woowacourse.movie.basic.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.basic.utils.getDummyMovie
import woowacourse.movie.basic.utils.getDummySeats
import woowacourse.movie.domain.model.Reservation
import java.time.LocalDateTime

class ReservationTest {
    @Test
    fun `예매할 좌석 자리가 A랭크 3자리일 때, 총 가격은 36,000 이다`() {
        // given & when
        val reservation =
            Reservation(
                id = 1,
                getDummyMovie(),
                3,
                seats = getDummySeats(),
                dateTime = LocalDateTime.now(),
            )

        // then
        assertThat(reservation.totalPrice).isEqualTo(36_000)
    }
}
