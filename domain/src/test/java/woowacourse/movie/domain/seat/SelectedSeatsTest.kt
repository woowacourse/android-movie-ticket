package woowacourse.movie.domain.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SelectedSeatsTest {
    @Test
    fun `1행, 3행 좌석이 하나씩 선택된 경우 할인이 적용되지 않은 총 금액은 25000원이다`() {
        val actual = SelectedSeats(
            setOf(
                Seat(1, 1),
                Seat(3, 1)
            )
        ).getAllPrice(
            LocalDateTime.of(2023, 4, 22, 17, 0)
        )

        assertThat(actual).isEqualTo(25000)
    }

    @Test
    fun `1행, 3행 좌석이 하나씩 선택되고 조조영화인 경우 총 금액은 22000원이다`() {
        val actual = SelectedSeats(
            setOf(
                Seat(1, 1),
                Seat(3, 1)
            )
        ).getAllPrice(
            LocalDateTime.of(2023, 4, 22, 9, 0)
        )

        assertThat(actual).isEqualTo(21000)
    }

    @Test
    fun `1행, 3행 좌석이 하나씩 선택되고 심야영화인 경우 총 금액은 22000원이다`() {
        val actual = SelectedSeats(
            setOf(
                Seat(1, 1),
                Seat(3, 1)
            )
        ).getAllPrice(
            LocalDateTime.of(2023, 4, 22, 20, 0)
        )

        assertThat(actual).isEqualTo(21000)
    }

    @Test
    fun `1행, 3행 좌석이 하나씩 선택되고 무비데이인 경우 총 금액은 22000원이다`() {
        val actual = SelectedSeats(
            setOf(
                Seat(1, 1),
                Seat(3, 1)
            )
        ).getAllPrice(
            LocalDateTime.of(2023, 4, 20, 17, 0)
        )

        assertThat(actual).isEqualTo(22500)
    }

    @Test
    fun `1행, 3행 좌석이 하나씩 선택되고 무비데이이면서 조조영화인 경우 총 금액은 22000원이다`() {
        val actual = SelectedSeats(
            setOf(
                Seat(1, 1),
                Seat(3, 1)
            )
        ).getAllPrice(
            LocalDateTime.of(2023, 4, 20, 9, 0)
        )

        assertThat(actual).isEqualTo(18500)
    }
}
