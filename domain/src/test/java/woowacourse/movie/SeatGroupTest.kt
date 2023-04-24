package woowacourse.movie

import com.woowacourse.domain.seat.Seat
import com.woowacourse.domain.seat.SeatColumn
import com.woowacourse.domain.seat.SeatGroup
import com.woowacourse.domain.seat.SeatRow
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SeatGroupTest {
    @Test
    fun `오름 차순으로 정렬할 수 있다`() {
        val actual = SeatGroup(
            listOf(
                Seat(SeatRow(3), SeatColumn(3)),
                Seat(SeatRow(2), SeatColumn(2)),
                Seat(SeatRow(1), SeatColumn(2)),
            )
        ).seats.sorted()

        val result = SeatGroup(
            listOf(
                Seat(SeatRow(1), SeatColumn(2)),
                Seat(SeatRow(2), SeatColumn(2)),
                Seat(SeatRow(3), SeatColumn(3)),
            )
        )
        assertThat(actual).isEqualTo(result.seats)
    }

    @Test
    fun `좌석을 추가할 수 있다`() {
        val actual = SeatGroup().add(Seat(SeatRow(1), SeatColumn(1))).seats
        assertThat(actual).isEqualTo(SeatGroup(listOf(Seat(SeatRow(1), SeatColumn(1)))).seats)
    }

    @Test
    fun `가지고 있는 좌석을 제거할 수 있다`() {
        val actual = SeatGroup(listOf(Seat(SeatRow(1), SeatColumn(1)))).remove(
            Seat(
                SeatRow(1),
                SeatColumn(1)
            )
        ).seats
        assertThat(actual).isEqualTo(emptyList<Seat>())
    }

    @Test
    fun `구매할 티켓의 갯수보다 작으면 좌석을 추가할 수 있다`() {
        val actual = SeatGroup().canAdd(1)
        assertThat(actual).isTrue
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2])
    fun `구매할 티켓의 갯수보다 같거나 많다면 좌석을 추가할 수 없다`(count: Int) {
        val actual = SeatGroup(List(2) { Seat(SeatRow(1), SeatColumn(1)) }).canAdd(count)
        assertThat(actual).isFalse
    }
}
