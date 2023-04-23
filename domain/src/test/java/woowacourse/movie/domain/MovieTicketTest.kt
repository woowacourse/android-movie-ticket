package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.seat.Rank
import woowacourse.movie.domain.seat.Seat
import java.time.LocalDateTime

class MovieTicketTest {
    @Test
    fun `티켓 금액이 26000원일 때 무비데이인 경우 예매 금액은 23400원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 10, 12, 0)),
            PeopleCount(2),
            setOf(),
            Price(26000)
        )

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(23400)
    }

    @Test
    fun `티켓 금액이 26000원일 때 조조 영화인 경우 예매 금액은 24000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 11, 10, 0)),
            PeopleCount(2),
            setOf(),
            Price(26000)
        )

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(24000)
    }

    @Test
    fun `티켓 금액이 26000원일 때 심야 영화인 경우 예매 금액은 24000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 11, 23, 0)),
            PeopleCount(2),
            setOf(),
            Price(26000)
        )

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(24000)
    }

    @Test
    fun `티켓 금액이 26000원일 때 무비 데이고 조조 영화인 경우 예매 금액은 21400원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(2),
            setOf(),
            Price(26000)
        )

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(21400)
    }

    @Test
    fun `1행 1열 좌석을 추가한다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(2)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.seats).isEqualTo(listOf(Seat(1, 1, Rank.A)))
    }

    @Test
    fun `1행 1열 좌석을 삭제한다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(2),
            setOf(Seat(1, 1, Rank.A))
        )

        ticket.cancelSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.seats).isEqualTo(emptyList<Seat>())
    }

    @Test
    fun `무비데이 및 조조,심야 영화가 아닐 때 A 등급 좌석을 예매하면 티켓 가격은 12000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(12000)
    }

    @Test
    fun `무비데이일 때 A 등급 좌석을 예매하면 티켓 가격은 10800원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(10800)
    }

    @Test
    fun `조조 영화일 때 A 등급 좌석을 예매하면 티켓 가격은 10000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(10000)
    }

    @Test
    fun `심야 영화일 때 A 등급 좌석을 예매하면 티켓 가격은 10000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 23, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(10000)
    }

    @Test
    fun `무비데이 이고 조조 영화일 때 A 등급 좌석을 예매하면 티켓 가격은 8800원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(8800)
    }

    @Test
    fun `무비데이 및 조조,심야 영화가 아닐 때 B 등급 좌석을 예매하면 티켓 가격은 10000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(10000)
    }

    @Test
    fun `무비데이일 때 B 등급 좌석을 예매하면 티켓 가격은 9000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(9000)
    }

    @Test
    fun `조조 영화일 때 B 등급 좌석을 예매하면 티켓 가격은 8000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(8000)
    }

    @Test
    fun `심야 영화일 때 B 등급 좌석을 예매하면 티켓 가격은 8000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 23, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(8000)
    }

    @Test
    fun `무비데이 이고 조조 영화일 때 B 등급 좌석을 예매하면 티켓 가격은 7000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(7000)
    }

    @Test
    fun `무비데이 및 조조,심야 영화가 아닐 때 S 등급 좌석을 예매하면 티켓 가격은 15000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.S))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(15000)
    }

    @Test
    fun `무비데이일 때 S 등급 좌석을 예매하면 티켓 가격은 13500원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.S))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(13500)
    }

    @Test
    fun `조조 영화일 때 S 등급 좌석을 예매하면 티켓 가격은 13000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.S))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(13000)
    }

    @Test
    fun `심야 영화일 때 S 등급 좌석을 예매하면 티켓 가격은 13000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 23, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.S))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(13000)
    }

    @Test
    fun `무비데이 이고 조조 영화일 때 S 등급 좌석을 예매하면 티켓 가격은 11500원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.S))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(11500)
    }

    @Test
    fun `무비데이 및 조조,심야 영화가 아닐 때 A, B, S 등급 좌석을 선택하고 B 등급 좌석을 취소하면 티켓 가격은 27000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))
        ticket.reserveSeat(Seat(1, 2, Rank.B))
        ticket.reserveSeat(Seat(1, 3, Rank.S))
        ticket.cancelSeat(Seat(1, 4, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(27000)
    }

    @Test
    fun `무비데이일 때 A, B, S 등급 좌석을 선택하고 B 등급 좌석을 취소하면 티켓 가격은 24300원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 13, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))
        ticket.reserveSeat(Seat(1, 2, Rank.B))
        ticket.reserveSeat(Seat(1, 3, Rank.S))
        ticket.cancelSeat(Seat(1, 4, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(24300)
    }

    @Test
    fun `조조 영화일 때 A, B, S 등급 좌석을 선택하고 B 등급 좌석을 취소하면 티켓 가격은 25000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))
        ticket.reserveSeat(Seat(1, 2, Rank.B))
        ticket.reserveSeat(Seat(1, 3, Rank.S))
        ticket.cancelSeat(Seat(1, 4, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(25000)
    }

    @Test
    fun `심야 영화일 때 A, B, S 등급 좌석을 선택하고 B 등급 좌석을 취소하면 티켓 가격은 25000원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 18, 23, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))
        ticket.reserveSeat(Seat(1, 2, Rank.B))
        ticket.reserveSeat(Seat(1, 3, Rank.S))
        ticket.cancelSeat(Seat(1, 4, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(25000)
    }

    @Test
    fun `무비데이 이고 조조 영화일 때 A, B, S 등급 좌석을 선택하고 B 등급 좌석을 취소하면 티켓 가격은 22300원이다`() {
        val ticket = MovieTicket(
            "title",
            TicketTime(LocalDateTime.of(2023, 4, 20, 9, 0)),
            PeopleCount(1)
        )

        ticket.reserveSeat(Seat(1, 1, Rank.A))
        ticket.reserveSeat(Seat(1, 2, Rank.B))
        ticket.reserveSeat(Seat(1, 3, Rank.S))
        ticket.cancelSeat(Seat(1, 4, Rank.B))

        assertThat(ticket.getDiscountPrice().amount).isEqualTo(22300)
    }
}
