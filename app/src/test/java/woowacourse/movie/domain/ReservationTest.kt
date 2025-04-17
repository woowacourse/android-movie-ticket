package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationTest {
    private lateinit var reservation: Reservation

    @BeforeEach
    fun setUp() {
        reservation =
            Reservation(
                "해리포터",
                1,
                LocalDateTime.of(
                    APRIL_THIRTIETH,
                    LocalTime.of(12, 0),
                ),
            )
    }

    @Test
    fun `총 가격을 계산한다`() {
        // given & when
        val actual = reservation.totalPrice()
        val expected = 13000

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예매할 티켓 수를 증가한다`() {
        // given & when
        val expected = reservation.count + 1
        val actual = reservation.addCount().count

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예매할 티켓 수가 2장인 경우 예매할 티켓 수를 감소한다`() {
        // given
        reservation.count = 2

        // when
        val expected = reservation.count - 1
        val actual = reservation.minusCount().count

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예매할 티켓 수가 1장인 경우 예매할 티켓 수가 감소하지 않는다`() {
        // given
        reservation.count = 1

        // when
        val expected = reservation.count
        val actual = reservation.minusCount().count

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예매할 날짜를 변경한다`() {
        // given
        val expected =
            LocalDateTime.of(
                MAY_FIRST,
                LocalTime.of(12, 0),
            )

        // when
        val actual = reservation.updateReservedTime(expected).reservedTime

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
