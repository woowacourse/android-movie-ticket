package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import woowacourse.movie.model.data.dto.Movie
import woowacourse.movie.model.reservation.ReservationCount
import woowacourse.movie.model.reservation.Ticket
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SelectedSeats
import java.time.LocalDate
import java.time.LocalDateTime

val movie1 =
    Movie(
        0,
        "해리 포터와 마법사의 돌",
        LocalDate.of(2024, 3, 1),
        LocalDate.of(2024, 3, 20),
        152,
        "해리",
    )

val movie2 = movie1.copy(title = "해리 포터와 마법사의 돌2")
val movie3 = movie1.copy(title = "해리 포터와 마법사의 돌3")

fun equalMovie(
    actual: Movie,
    expected: Movie,
) {
    assertThat(actual.posterImageId).isEqualTo(expected.posterImageId)
    assertThat(actual.title).isEqualTo(actual.title)
    assertThat(actual.startScreeningDate).isEqualTo(actual.startScreeningDate)
    assertThat(actual.endScreeningDate).isEqualTo(actual.endScreeningDate)
    assertThat(actual.runningTime).isEqualTo(actual.runningTime)
    assertThat(actual.synopsis).isEqualTo(actual.synopsis)
}

fun SelectedSeats(
    count: Int,
    vararg seat: Seat,
): SelectedSeats {
    return SelectedSeats(ReservationCount(count)).apply {
        seat.forEach { select(it) }
    }
}

val ticket1 =
    Ticket(
        0L,
        LocalDateTime.of(2024, 4, 20, 9, 0),
        SelectedSeats(ReservationCount(3)),
    )

val ticket2 = ticket1.copy(movieId = 1L)
val ticket3 = ticket1.copy(movieId = 2L)

fun equalTicket(
    actual: Ticket,
    expected: Ticket,
) {
    assertThat(actual.movieId).isEqualTo(expected.movieId)
    assertThat(actual.screeningDateTime).isEqualTo(actual.screeningDateTime)
    assertThat(actual.selectedSeats).isEqualTo(actual.selectedSeats)
}
