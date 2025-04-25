package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import woowacourse.movie.domain.model.reservation.ReservationCount
import woowacourse.movie.domain.model.reservation.ReservationInfo
import java.time.LocalDateTime

class ReservationInfoTest {
    @Test
    fun `예약 정보를 생성할 수 있다`() {
        val title = "해리 포터와 마법사의 돌"
        val reservationDateTime = LocalDateTime.of(2025, 4, 30, 20, 0)
        val reservationCount = ReservationCount(2)

        val reservationInfo = ReservationInfo(title, reservationDateTime, reservationCount)

        assertAll(
            { assertThat(reservationInfo.title).isEqualTo("해리 포터와 마법사의 돌") },
            {
                assertThat(reservationInfo.reservationDateTime).isEqualTo(
                    LocalDateTime.of(
                        2025,
                        4,
                        30,
                        20,
                        0,
                    ),
                )
            },
            { assertThat(reservationInfo.reservationCount).isEqualTo(ReservationCount(2)) },
        )
    }
}
