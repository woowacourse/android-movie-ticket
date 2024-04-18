package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationCountTest {
    @Test
    fun `예매 인원의 기본 값은 1이다`() {
        // given
        val reservationCount = ReservationCount()

        // when
        val actual = reservationCount.count

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `예매 인원을 한 명 증가시킨다`() {
        // given
        var reservationCount = ReservationCount()

        // when
        reservationCount++
        val actual = reservationCount.count

        // then
        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun `예매 인원의 최대는 50명이다`() {
        // given
        var reservationCount = ReservationCount(50)

        // when
        reservationCount++
        val actual = reservationCount.count

        // then
        assertThat(actual).isEqualTo(50)
    }

    @Test
    fun `예매 인원을 한 명 감소시킨다`() {
        // given
        var reservationCount = ReservationCount(2)

        // when
        reservationCount--
        val actual = reservationCount.count

        // then
        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `예매 인원의 최소는 1명이다`() {
        // given
        var reservationCount = ReservationCount(1)

        // when
        reservationCount--
        val actual = reservationCount.count

        // then
        assertThat(actual).isEqualTo(1)
    }
}
