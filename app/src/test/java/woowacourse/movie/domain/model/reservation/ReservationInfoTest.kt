package woowacourse.movie.domain.model.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.model.cinema.screen.Seat
import woowacourse.movie.domain.model.cinema.screen.SeatType
import java.time.LocalDateTime

class ReservationInfoTest {
    private lateinit var fakeReservationInfo: ReservationInfo

    @BeforeEach
    fun setUp() {
        fakeReservationInfo =
            ReservationInfo(
                "해리 포터와 마법사의 돌",
                LocalDateTime.of(2025, 4, 30, 20, 0),
                ReservationCount(2),
            )
    }

    @Test
    fun `예약 좌석을 추가할 수 있다`() {
        val seat = Seat(0, 1, SeatType.B_CLASS)
        fakeReservationInfo.updateSeats(seat)
        assertThat(fakeReservationInfo.seats).contains(seat)
    }

    @Test
    fun `예약 좌석을 제거할 수 있다`() {
        val seat = Seat(0, 1, SeatType.B_CLASS)
        fakeReservationInfo.updateSeats(seat)
        fakeReservationInfo.updateSeats(seat)
        assertThat(fakeReservationInfo.seats).doesNotContain(seat)
    }

    @Test
    fun `예매 인원 보다 더 많은 좌석을 선택하면 예외가 발생한다`() {
        val seat = Seat(0, 1, SeatType.B_CLASS)
        fakeReservationInfo.updateSeats(seat)
        fakeReservationInfo.updateSeats(seat.copy(1))

        assertThrows<IllegalArgumentException> {
            fakeReservationInfo.updateSeats(seat.copy(2))
        }
    }

    @Test
    fun `canPublish 호출 시 좌석 수와 예약 인원 수가 일치하면 true 를 반환한다`() {
        fakeReservationInfo.updateSeats(Seat(0, 1, SeatType.B_CLASS))
        fakeReservationInfo.updateSeats(Seat(0, 2, SeatType.B_CLASS))
        assertThat(fakeReservationInfo.canPublish()).isTrue()
    }

    @Test
    fun `canPublish 호출 시 좌석 수와 예약 인원 수가 다르면 false 를 반환한다`() {
        assertThat(fakeReservationInfo.canPublish()).isFalse()
    }
}
