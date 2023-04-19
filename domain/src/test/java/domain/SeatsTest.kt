package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SeatsTest {

    @Test
    fun `좌석을 선택하면 빈 리스트에 좌석이 추가된다`() {
        val seats = Seats()
        seats.add(Seat(Position.of(1), TicketPrice.of(Position.of(1))))

        assertThat(seats.seats.size).isEqualTo(1)
    }

    @Test
    fun `좌석을 제거하면 리스트에서 좌석이 삭제된다`() {
        val seats = Seats(
            listOf(
                Seat(Position.of(0), TicketPrice.of(Position.of(0))),
                Seat(Position.of(1), TicketPrice.of(Position.of(1))),
            ),
        )

        seats.remove(Seat(Position.of(1), TicketPrice.of(Position.of(1))))

        assertThat(seats.seats.size).isEqualTo(1)
    }

    @Test
    fun `좌석이 리스트에 포함되어 있으면 true를 반환`() {
        val seats = Seats(
            listOf(
                Seat(Position.of(0), TicketPrice.of(Position.of(0))),
                Seat(Position.of(1), TicketPrice.of(Position.of(1))),
            ),
        )
        val seat = Seat(Position.of(1), TicketPrice.of(Position.of(1)))

        assertThat(seats.containsSeat(seat)).isTrue
    }

    @Test
    fun `좌석이 리스트에 포함되어 있지 않으면 false를 반환`() {
        val seats = Seats(
            listOf(
                Seat(Position.of(0), TicketPrice.of(Position.of(0))),
                Seat(Position.of(1), TicketPrice.of(Position.of(1))),
            ),
        )
        val seat = Seat(Position.of(2), TicketPrice.of(Position.of(2)))

        assertThat(seats.containsSeat(seat)).isFalse
    }

    @Test
    fun `1행 좌석 하나, 3행 좌석 하나, 5행 좌석 하나가 있고 2023년 3월 1일, 시간이 9시이면 총 가격은 31000원이다`() {
        val seats = Seats(
            listOf(
                Seat(Position(1, 2), TicketPrice.of(Position(1, 2))),
                Seat(Position(3, 2), TicketPrice.of(Position(3, 2))),
                Seat(Position(5, 2), TicketPrice.of(Position(5, 2))),
            ),
        )
        val dateTime = LocalDateTime.of(LocalDate.of(2023, 3, 1), LocalTime.of(9, 0))

        assertThat(seats.caculateSeatPrice(dateTime)).isEqualTo(31000)
    }

    @Test
    fun `1행 좌석 하나, 3행 좌석 하나, 5행 좌석 하나가 있고 2023년 3월 10일, 시간이 13시이면 총 가격은 33300원이다`() {
        val seats = Seats(
            listOf(
                Seat(Position(1, 2), TicketPrice.of(Position(1, 2))),
                Seat(Position(3, 2), TicketPrice.of(Position(3, 2))),
                Seat(Position(5, 2), TicketPrice.of(Position(5, 2))),
            ),
        )
        val dateTime = LocalDateTime.of(LocalDate.of(2023, 3, 10), LocalTime.of(13, 0))

        assertThat(seats.caculateSeatPrice(dateTime)).isEqualTo(33300)
    }

    @Test
    fun `1행 좌석 하나, 3행 좌석 하나, 5행 좌석 하나가 있고 2023년 3월 10일, 시간이 9시이면 총 가격은 27300원이다`() {
        val seats = Seats(
            listOf(
                Seat(Position(1, 2), TicketPrice.of(Position(1, 2))),
                Seat(Position(3, 2), TicketPrice.of(Position(3, 2))),
                Seat(Position(5, 2), TicketPrice.of(Position(5, 2))),
            ),
        )
        val dateTime = LocalDateTime.of(LocalDate.of(2023, 3, 10), LocalTime.of(9, 0))

        assertThat(seats.caculateSeatPrice(dateTime)).isEqualTo(27300)
    }
}
