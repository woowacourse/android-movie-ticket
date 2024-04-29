package woowacourse.movie.basic.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.basic.utils.getDummyMovie
import woowacourse.movie.basic.utils.getDummySeats
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatRank
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

    @Test
    fun `예매할 좌석 자리가 S랭크 3자리일 때, 총 가격은 45,000 이다`() {
        // given & when
        val reservation =
            Reservation(
                id = 1,
                getDummyMovie(),
                3,
                seats =
                    listOf(
                        Seat("A", 1, SeatRank.S),
                        Seat("A", 2, SeatRank.S),
                        Seat("A", 3, SeatRank.S),
                    ),
                dateTime = LocalDateTime.now(),
            )

        // then
        assertThat(reservation.totalPrice).isEqualTo(45_000)
    }

    @Test
    fun `예매할 좌석 자리가 B랭크 3자리일 때, 총 가격은 30,000 이다`() {
        // given & when
        val reservation =
            Reservation(
                id = 1,
                getDummyMovie(),
                3,
                seats =
                    listOf(
                        Seat("A", 1, SeatRank.B),
                        Seat("A", 2, SeatRank.B),
                        Seat("A", 3, SeatRank.B),
                    ),
                dateTime = LocalDateTime.now(),
            )

        // then
        assertThat(reservation.totalPrice).isEqualTo(30_000)
    }
}
