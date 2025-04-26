package woowacourse.movie.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.BookingStatus
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.domain.Title
import java.time.LocalDate
import java.time.LocalDateTime


object MovieFixture {
    val movie = Movie(
        Title("해리포터와 마법사의 돌"),
        R.drawable.movie_poster,
        ScreeningPeriod(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 25),
        ),
        152,
    )

    val dates = listOf(
        LocalDate.of(2025, 4, 1),
        LocalDate.of(2025, 4, 2),
        LocalDate.of(2025, 4, 3),
        LocalDate.of(2025, 4, 4),
        LocalDate.of(2025, 4, 5)
    )

    val dates2 = listOf(
        LocalDate.of(2025, 4, 3),
        LocalDate.of(2025, 4, 4),
        LocalDate.of(2025, 4, 5)
    )

    val dates3 = listOf(
        LocalDate.of(2025, 4, 5)
    )

    val movies = mapOf<Title, Movie>(
        Title("해리포터와 마법사의 돌") to Movie(
            Title("해리포터와 마법사의 돌"),
            R.drawable.movie_poster,
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            152,
        ),
        Title("포니") to Movie(
            Title("포니"),
            R.drawable.movie_poster,
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            152,
        )
    )

    val movies2 = mapOf<Title, Movie>(
        Title("해리포터와 마법사") to Movie(
            Title("해리포터와 마법사의 돌"),
            R.drawable.movie_poster,
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            152,
        ),
    )

    val listMovies = listOf<Movie>(
        Movie(
            Title("해리포터와 마법사의 돌"),
            R.drawable.movie_poster,
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            152,
        ),
        Movie(
            Title("포니"),
            R.drawable.movie_poster,
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            152,
        )
    )

    val MAX_MOVIES = Movies(
        (0 until 10_000).associate { index ->
            Title("해리포터와 마법사의 돌 $index") to Movie(
                Title("해리포터와 마법사의 돌 $index"),
                R.drawable.movie_poster,
                ScreeningPeriod(
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                ),
                152
            )
        }
    )

    val BOOKING_STATUS =
        BookingStatus(movie, true, TicketCount(2), LocalDateTime.of(2025, 4, 30, 9, 0, 0))
}
