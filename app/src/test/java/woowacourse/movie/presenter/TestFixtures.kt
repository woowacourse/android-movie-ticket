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

val MOVIE_TICKET_B1_C3: MovieTicket =
    MovieTicket(
        "라라랜드",
        LocalDate.of(2025, 4, 1),
        MovieTime(),
        listOf(Seat(1, 0), Seat(2, 2)),
    )

private val POSTER_IMAGES =
    listOf(
        R.drawable.harry_potter_azkaban,
        R.drawable.harry_potter_final_1,
        R.drawable.harry_potter_final_2,
        R.drawable.harry_potter_glass,
        R.drawable.harry_potter_knight,
        R.drawable.harry_potter_prince,
        R.drawable.harry_potter_rock,
        R.drawable.harry_potter_room,
    )

val MOVIES: List<Movie> =
    (1..1000).map { id ->
        val poster = POSTER_IMAGES[(id - 1) % POSTER_IMAGES.size]
        Movie(
            id = id.toLong(),
            title = "해리포터 $id",
            poster = poster,
            startDate = LocalDate.of(2025, 4, (id % 28) + 1),
            endDate = LocalDate.of(2025, 5, (id % 28) + 1),
            runningTime = 100 + (id % 60),
        )
    }
