package woowacourse.movie.domain.model.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ReservationCountTest {
    @Test
    fun `기본 예매 인원은 1명이다`() {
        val reservationCount = ReservationCount()
        val expected = 1
        assertThat(reservationCount.value).isEqualTo(expected)
    }

    @Test
    fun `초기 예매 인원 수를 지정할 수 있다`() {
        val count = ReservationCount(3)
        val expected = 3
        assertThat(count.value).isEqualTo(expected)
    }

    @Test
    fun `최소 예매 인원보다 작으면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            ReservationCount(0)
        }
    }

    @Test
    fun `현재 예매 인원에서 Int를 더할 수 있다`() {
        val count = ReservationCount(2)
        val result = count + 3
        val expected = 5
        assertThat(result.value).isEqualTo(expected)
    }

    @Test
    fun `현재 예매 인원에서 Int를 뺄 수 있다`() {
        val count = ReservationCount(5)
        val result = count - 2
        val expected = 3
        assertThat(result.value).isEqualTo(expected)
    }

    @Test
    fun `빼기 연산 결과가 최소값 미만이면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> {
            ReservationCount(2) - 2
        }
    }
}
