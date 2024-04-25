package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SeatingSystemTest {
    private lateinit var seatingSystem: SeatingSystem

    @BeforeEach
    fun setup() {
        seatingSystem = SeatingSystem()
    }

    @Test
    fun `전체 좌석은 5행 4열로 구성되어 있다`() {
        val seats = seatingSystem.seats
        assertThat(seats.size).isEqualTo(5 * 4)
    }

    @Test
    fun `사용자가 좌석을 선택하면 선택된 좌석이 저장된다`() {
        seatingSystem.selectSeat(0)

        val actual = seatingSystem.selectedSeats
        val expected = listOf(Seat(0, 0))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `사용자가 좌석을 선택 취소하면 선택된 좌석이 취소된다`() {
        seatingSystem.selectSeat(0)
        seatingSystem.selectSeat(1)
        seatingSystem.unSelectSeat(1)

        val actual = seatingSystem.selectedSeats
        val expected = setOf(Seat(0, 0))
        assertThat(actual).isEqualTo(expected)
    }
}
