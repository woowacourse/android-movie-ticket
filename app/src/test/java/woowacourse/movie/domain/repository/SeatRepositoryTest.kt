package woowacourse.movie.domain.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MockSeats
import woowacourse.movie.data.SeatRepositoryImpl

class SeatRepositoryTest {
    private val repository: SeatRepository = SeatRepositoryImpl()

    @Test
    fun `getSeats 메서드가 적절한 좌석 리스트를 반환하는지 확인한다`() {
        // When
        val seats = repository.getAvailableSeats()

        // Then
        assertEquals(MockSeats.sampleSeats, seats)
    }

    @Test
    fun `getSeat 메서드가 적절한 좌석을 반환하는지 확인한다`() {
        // Given
        val expectedSeat = MockSeats.sampleSeats[1][2]

        // When
        val seat = repository.getAvailableSeat(1, 2)

        // Then
        assertEquals(expectedSeat, seat)
    }

    @Test
    fun `getSeat 메서드가 존재하지 않는 좌석을 요청할 때 defaultSeat을 반환하는지 확인한다`() {
        // Given
        val expectedDefaultSeat = MockSeats.defaultSeat

        // When
        val seat = repository.getAvailableSeat(10, 10)

        // Then
        assertEquals(expectedDefaultSeat, seat)
    }
}
