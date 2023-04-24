package woowacourse.movie.domain.model.tools

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import woowacourse.movie.domain.model.rules.SeatGradeRules
import woowacourse.movie.domain.model.tools.seat.Location
import woowacourse.movie.domain.model.tools.seat.Seat
import woowacourse.movie.domain.model.tools.seat.SeatRow
import woowacourse.movie.domain.model.tools.seat.Seats
import java.time.LocalDate
import java.time.LocalDateTime

class MovieTest {

    // fake constructor
    private fun Seats(vararg locations: Location): Seats {
        val seats = locations.map {
            Seat(it, SeatGradeRules.getSeatGradeByRow(it))
        }
        return Seats(seats.toSet())
    }

    @Test
    fun `무비에 해당하는 티켓을 반환한다`() {
        val start = LocalDate.of(2023, 4, 12)
        val end = LocalDate.of(2023, 4, 15)
        val screeningDate = LocalDateTime.of(2023, 4, 12, 0, 0)
        val expected = Ticket(
            1L,
            screeningDate,
            3,
            Seats(
                Location(SeatRow.A, 1),
                Location(SeatRow.B, 1),
                Location(SeatRow.C, 1),
            ),
        )

        val movie = Movie(
            id = 1L,
            title = "해리포터",
            screeningStartDate = start,
            screeningEndDate = end,
            runningTime = 1,
            description = "",
        )

        val actual = movie.reserve(
            screeningDate,
            TicketCount(3),
            Seats(
                Location(SeatRow.A, 1),
                Location(SeatRow.B, 1),
                Location(SeatRow.C, 1),
            ),
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `티켓의 상영일이 영화 시작 상영일과 끝 상영일 사이에 있어야 한다`() {
        val start = LocalDate.of(2023, 4, 12)
        val end = LocalDate.of(2023, 4, 15)
        val screeningDate = LocalDateTime.of(2023, 4, 10, 0, 0)
        val expected = Ticket(
            1L,
            screeningDate,
            3,
            Seats(
                Location(SeatRow.A, 1),
                Location(SeatRow.B, 1),
                Location(SeatRow.C, 1),
            ),
        )

        val movie = Movie(
            id = 1L,
            title = "해리포터",
            screeningStartDate = start,
            screeningEndDate = end,
            runningTime = 1,
            description = "",
        )

        assertThrows<IllegalArgumentException> {
            movie.reserve(
                screeningDate,
                TicketCount(3),
                Seats(
                    Location(SeatRow.A, 1),
                    Location(SeatRow.B, 1),
                    Location(SeatRow.C, 1),
                ),
            )
        }
    }

    @Test
    fun `시작일과 종료일을 받으면 해당 기간을 리스트로 반환한다`() {
        // given
        val start = LocalDate.of(2023, 4, 12)
        val end = LocalDate.of(2023, 4, 15)
        val expected = listOf<LocalDate>(
            LocalDate.of(2023, 4, 12),
            LocalDate.of(2023, 4, 13),
            LocalDate.of(2023, 4, 14),
            LocalDate.of(2023, 4, 15),
        )

        val movie = Movie(
            id = 1L,
            title = "",
            screeningStartDate = start,
            screeningEndDate = end,
            runningTime = 1,
            description = "",
        )

        // when
        val actual = movie.getScreeningDates()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
