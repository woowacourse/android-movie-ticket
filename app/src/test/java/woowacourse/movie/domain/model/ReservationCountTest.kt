package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ReservationCountTest {
    @Test
    fun `영화_예매_인원_수가_최소_1명_미만이면_예외가_발생한다`() {
        assertThrows<IllegalArgumentException> { ReservationCount(0) }
    }

    @Test
    fun `현재_예매_인원이_2명일때_1을_더하면_예매_인원은_3명이_된다`() {
        val reservationCount = ReservationCount(2)
        val actual = reservationCount + 1
        val expected = 3

        assertThat(actual.value).isEqualTo(expected)
    }

    @Test
    fun `현재_예매_인원이_3명일때_1을_빼면_예매_인원은_2명이_된다`() {
        val reservationCount = ReservationCount(3)
        val actual = reservationCount - 1
        val expected = 2

        assertThat(actual.value).isEqualTo(expected)
    }
}
