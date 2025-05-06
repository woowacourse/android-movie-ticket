package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDateTime

class ReservationInfoTest {
    private val reservationInfo =
        ReservationInfo(
            title = "해리 포터와 마법사의 돌",
            reservationDateTime = LocalDateTime.of(2025, 4, 15, 11, 0),
            reservationCount = ReservationCount(2),
        )

    @Test
    fun `예약_정보를_생성_할_수_있다`() {
        assertAll(
            { assertThat(reservationInfo.title).isEqualTo("해리 포터와 마법사의 돌") },
            {
                assertThat(reservationInfo.reservationDateTime).isEqualTo(
                    LocalDateTime.of(2025, 4, 15, 11, 0),
                )
            },
            { assertThat(reservationInfo.reservationCount.value).isEqualTo(2) },
        )
    }

    @Test
    fun `영화_예매_인원_수에_따라_티켓_금액을_계산할_수_있다`() {
        val ticketPrice = reservationInfo.totalPrice()
        val expected = 26000
        assertThat(ticketPrice).isEqualTo(expected)
    }
}
