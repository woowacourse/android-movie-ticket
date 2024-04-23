package woowacourse.movie.model.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ReservationCountTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 13, 15, 50])
    fun `예매 인원은 1~50 사이여야 한다`(count: Int) {
        assertDoesNotThrow { ReservationCount(count) }
    }

    @Test
    fun `예매 인원의 최소보다 작은 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { ReservationCount(0) }
    }

    @Test
    fun `예매 인원의 최대보다 큰 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { ReservationCount(51) }
    }

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
    fun `예매 인원의 최소인 경우는 더이상 감소하지 않는다`() {
        // given
        var reservationCount = ReservationCount(1)

        // when
        reservationCount--
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
    fun `예매 인원의 최대인 경우는 더이상 증가하지 않는다`() {
        // given
        var reservationCount = ReservationCount(50)

        // when
        reservationCount++
        val actual = reservationCount.count

        // then
        assertThat(actual).isEqualTo(50)
    }
}
