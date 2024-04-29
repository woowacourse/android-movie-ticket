package woowacourse.movie.basic.presentation.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatRank
import woowacourse.movie.presentation.model.UserSeat

class UserSeatTest {
    @Test
    fun `removeAt 함수는 주어진 Seat을 제거한 새로운 UserSeat 객체를 반환해야 한다`() {
        // Given
        val seat1 = Seat("A", 1, SeatRank.A)
        val seat2 = Seat("A", 2, SeatRank.A)
        val seat3 = Seat("A", 3, SeatRank.A)
        val userSeat = UserSeat(listOf(seat1, seat2, seat3))

        // When
        val newUserSeat = userSeat.removeAt(seat2)

        // Then
        assertEquals(listOf(seat1, seat3), newUserSeat.seats)
    }

    @Test
    fun `plus 연산자는 새로운 Seat을 추가한 새로운 UserSeat 객체를 반환해야 한다`() {
        // Given
        val seat1 = Seat("A", 1, SeatRank.A)
        val seat2 = Seat("A", 2, SeatRank.A)
        val seat3 = Seat("A", 3, SeatRank.A)
        val userSeat = UserSeat(listOf(seat1, seat2))

        // When
        val newUserSeat = userSeat + seat3

        // Then
        assertEquals(listOf(seat1, seat2, seat3), newUserSeat.seats)
    }
}
