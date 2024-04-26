package woowacourse.movie.model.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReservationDetailTest {
    @Test
    fun `좌석을 선택한다`() {
        // given
        val reservationDetail = ReservationDetail(10)

        // when
        reservationDetail.addSeat(0, 0)
        val actual = reservationDetail.selectedSeat[0]

        // then=
        assertThat(actual).isEqualTo(Seat(0, 0))
    }

    @Test
    fun `예매 인원만큼 좌석을 선택한 경우 더이상 선택되지 않는다`() {
        // given
        val reservationDetail = ReservationDetail(1)
        reservationDetail.addSeat(0, 0)

        // when
        reservationDetail.addSeat(1, 1)
        val actual = reservationDetail.selectedSeat.contains(Seat(1, 1))

        // then=
        assertThat(actual).isFalse()
    }

    @Test
    fun `좌석을 제거한다`() {
        // given
        val reservationDetail = ReservationDetail(10)
        reservationDetail.addSeat(0, 0)

        // when
        reservationDetail.removeSeat(0, 0)
        val actual = reservationDetail.selectedSeat.contains(Seat(0, 0))

        // then=
        assertThat(actual).isFalse()
    }

    @Test
    fun `선택한 좌석들의 총 가격을 계산한다`() {
        // given
        val reservationDetail = ReservationDetail(10)
        reservationDetail.addSeat(0, 0)
        reservationDetail.addSeat(1, 1)

        // when
        val actual = reservationDetail.totalSeatAmount()
        val expected = Seat(0, 0).price() + Seat(1, 1).price()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `예매 인원 수 만큼 좌석을 선택한 경우 true를 반환한다`() {
        // given
        val reservationDetail = ReservationDetail(2)
        reservationDetail.addSeat(0, 0)
        reservationDetail.addSeat(1, 1)

        // when
        val actual = reservationDetail.checkSelectCompletion()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `예매 인원 수 만큼 좌석을 선택하지 않은 경우 false를 반환한다`() {
        // given
        val reservationDetail = ReservationDetail(3)
        reservationDetail.addSeat(0, 0)
        reservationDetail.addSeat(1, 1)

        // when
        val actual = reservationDetail.checkSelectCompletion()

        // then
        assertThat(actual).isFalse()
    }
}
