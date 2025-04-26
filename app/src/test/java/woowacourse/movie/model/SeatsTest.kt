package woowacourse.movie.model


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SeatsTest {
    @Test
    fun `선택되지 않은 좌석이 포함될 수 없다`() {

        assertThrows<IllegalArgumentException> {
            Seats(
                listOf(Seat("A1", false))
            )
        }
    }

    @Test
    fun `선택된 좌석별 등급에 따라 예매가격을 확인할 수 있다`() {
        val seats = Seats(
            listOf(
                Seat("E1", true),
                Seat("A1", true),
                Seat("C1", true)
            )
        )

        val expected = 37000
        assertEquals(expected, seats.amount)
    }

    @Test
    fun `좌석을 선택하면, 좌석의 선택 여부에 따라 리스트에 업데이트 된다`() {
        val seats = Seats(
            emptyList()
        )
        val seat = Seat("A1")
        val newSeats = seats.toggle(seat, 3)

        val expectedSeat = Seat("A1", true)
        val expected = Seats(listOf(expectedSeat))
        assertEquals(expected, newSeats)
    }

    @Test
    fun `현재 선택한 좌석의 수가 예매 인원수보다 많을 경우 추가되지 않는다`() {
        val headCount = 2
        val seats = Seats(
            listOf(
                Seat("E1", true),
                Seat("A1", true),
            )
        )
        val seat = Seat("A3")
        val newSeats = seats.toggle(seat, headCount)

        val expected = seats
        assertEquals(expected, newSeats)
    }

    @Test
    fun `현재 선택한 좌석의 수가 예매 인원수보다 적을 경우 추가된다`() {
        val headCount = 10
        val seats = Seats(
            listOf(
                Seat("E1", true),
                Seat("A1", true),
            )
        )
        val seat = Seat("A3")
        val newSeats = seats.toggle(seat, headCount)

        val expected = Seats(
            listOf(
                Seat("E1", true),
                Seat("A1", true),
                Seat("A3", true)
            )
        )
        assertEquals(expected, newSeats)
    }
}