package woowacourse.movie.domain.model.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReservationCountTest {
    private lateinit var reservationCount: ReservationCount

    @BeforeEach
    fun setUp() {
        reservationCount = ReservationCount()
    }

    @Test
    fun `예매할 티켓의 초기 인원 수는 1명이다`() {
        assertThat(reservationCount.count).isEqualTo(ReservationCount.MIN_RESERVATION_COUNT)
    }

    @Test
    fun `예매할 티켓의 인원 수를 증가시킨다`() {
        // when
        reservationCount.plusCount()

        // then
        assertThat(reservationCount.count).isEqualTo(ReservationCount.MIN_RESERVATION_COUNT + 1)
    }

    @Test
    fun `예매할 티켓의 인원 수는 20명을 초과할 수 없다`() {
        // when
        repeat(ReservationCount.MAX_RESERVATION_COUNT) {
            reservationCount.plusCount()
        }
        reservationCount.plusCount()

        // then
        assertThat(reservationCount.count).isEqualTo(ReservationCount.MAX_RESERVATION_COUNT)
    }

    @Test
    fun `예매할 티켓의 인원 수를 감소시킨다`() {
        // given
        reservationCount.plusCount()

        // when
        reservationCount.minusCount()

        // then
        assertThat(reservationCount.count).isEqualTo(ReservationCount.MIN_RESERVATION_COUNT)
    }

    @Test
    fun `예매할 티켓의 인원 수는 1명 미만이 될 수 없다`() {
        // when
        reservationCount.minusCount()

        // then
        assertThat(reservationCount.count).isEqualTo(ReservationCount.MIN_RESERVATION_COUNT)
    }
}
