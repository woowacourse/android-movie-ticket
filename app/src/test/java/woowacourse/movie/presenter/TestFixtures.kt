package woowacourse.movie.presenter

import woowacourse.movie.R
import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.MovieDate
import woowacourse.movie.model.movie.MovieTime
import woowacourse.movie.model.movie.MovieToReserve
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.ticket.MovieTicket
import woowacourse.movie.model.ticket.TicketCount
import java.time.LocalDate

val MOVIE: Movie =
    Movie(
        1,
        "라라랜드",
        R.drawable.lalaland,
        LocalDate.of(2025, 4, 1),
        LocalDate.of(2025, 5, 30),
        120,
    )

val MOVIE_TO_RESERVE: MovieToReserve =
    MovieToReserve(
        1,
        "라라랜드",
        MovieDate(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 5, 30),
        ),
        MovieTime(),
        TicketCount(2),
    )

val SEAT_2_3 = Seat(2, 3)
val SEAT_2_2 = Seat(2, 2)

val MOVIE_TICKET_B1_C3: MovieTicket =
    MovieTicket(
        "라라랜드",
        LocalDate.of(2025, 4, 1),
        MovieTime(),
        listOf(Seat(1, 0), Seat(2, 2)),
    )
