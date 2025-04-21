package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ReservationCountTest {
    @Test
    fun `영화 예매 인원 수가 최소 1명 미만이면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> { ReservationCount(0) }
    }

    @Test
    fun `현재 예매 인원이 2명일때 1을 더하면 예매 인원은 3명이 된다`() {
        val reservationCount = ReservationCount(2)
        val actual = reservationCount + 1
        val expected = 3

        assertThat(actual.count).isEqualTo(expected)
    }

    @Test
    fun `현재 예매 인원이 3명일때 1을 뺘면 예매 인원은 2명이 된다`() {
        val reservationCount = ReservationCount(3)
        val actual = reservationCount - 1
        val expected = 2

        assertThat(actual.count).isEqualTo(expected)
    }
}
