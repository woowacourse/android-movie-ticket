package woowacourse.movie.model.seat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.model.SelectedSeats

class SelectedSeatsTest {
    @Test
    fun `4행 2열을 선택한다`() {
        val selectedSeats = SelectedSeats(count = 1)
        assertDoesNotThrow {
            selectedSeats.select(Seat(4, 2))
        }
    }

    @Test
    fun `1행 3열을 선택 해제한다`() {
        val selectedSeats = SelectedSeats(count = 1, Seat(1, 3))
        assertDoesNotThrow {
            selectedSeats.unselect(Seat(1, 3))
        }
    }

    @Test
    fun `이미 선택한 좌석을 다시 선택하는 경우 예외가 발생한다`() {
        val selectedSeats = SelectedSeats(count = 1, Seat(3, 3))
        assertThrows<IllegalArgumentException> {
            selectedSeats.select(Seat(3, 3))
        }
    }

    @Test
    fun `선택하지 않은 좌석을 선택 해제하는 경우 예외가 발생한다`() {
        val selectedSeats = SelectedSeats(count = 1)
        assertThrows<IllegalArgumentException> {
            selectedSeats.unselect(Seat(3, 3))
        }
    }

    @Test
    fun `예매 인원이 3명이고 좌석을 1개 선택한 경우 좌석을 더 선택할 수 있다`() {
        // given
        val selectedSeats = SelectedSeats(count = 3, Seat(3, 3))

        // when
        val actual = selectedSeats.isSelectable()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `예매 인원이 3명이고 좌석을 3개 선택한 경우 좌석을 더 선택할 수 없다`() {
        // given
        val selectedSeats =
            SelectedSeats(
                count = 3,
                Seat(1, 3),
                Seat(2, 3),
                Seat(3, 3),
            )

        // when
        val actual = selectedSeats.isSelectable()

        // then
        assertThat(actual).isFalse
    }

    @Test
    fun `예매 인원이 5명이고 좌석을 2개 선택한 경우 예매를 완료하지 않는다`() {
        // given
        val selectedSeats =
            SelectedSeats(
                count = 5,
                Seat(1, 3),
                Seat(2, 3),
            )

        // when
        val actual = selectedSeats.isConfirm()

        // then
        assertThat(actual).isFalse
    }

    @Test
    fun `예매 인원이 5명이고 좌석을 5개 선택한 경우 예매를 완료한다`() {
        // given
        val selectedSeats =
            SelectedSeats(
                5,
                Seat(1, 3),
                Seat(2, 3),
                Seat(3, 3),
                Seat(4, 3),
                Seat(5, 3),
            )

        // when
        val actual = selectedSeats.isConfirm()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `1행 2열과 3행 3열, 4행 1열을 선택했다면 총 예매 금액은 40,000원이다`() {
        // given
        val selectedSeats =
            SelectedSeats(
                3,
                Seat(1, 2),
                Seat(3, 3),
                Seat(4, 1),
            )

        // when
        val actual = selectedSeats.amount().amount

        // then
        assertThat(actual).isEqualTo(40_000)
    }
}
