package woowacourse.movie.domain.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SelectedSeatsTest {
    @Test
    fun `1행, 3행 좌석이 하나씩 선택된 경우 총 금액은 25000원이다`() {
        val actual = SelectedSeats(
            setOf(
                Seat(1, 1),
                Seat(3, 1)
            )
        ).getAllPrice()

        assertThat(actual).isEqualTo(25000)
    }
}
