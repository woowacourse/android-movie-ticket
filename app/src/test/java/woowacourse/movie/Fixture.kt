package woowacourse.movie

import woowacourse.movie.domain.model.BookingInfo
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.MovieDate
import woowacourse.movie.domain.model.MovieTime
import java.time.LocalTime

val MOVIE_01 =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = MovieDate(2025, 4, 1),
        endDate = MovieDate(2025, 4, 25),
        runningTime = 152,
    )

val BOOKING_INFO_01 =
    BookingInfo(
        movie =
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = MovieDate(2025, 4, 1),
                endDate = MovieDate(2025, 4, 25),
                runningTime = 152,
            ),
    ).apply {
        updateDate(MovieDate(2025, 4, 1))
        updateMovieTime(MovieTime(LocalTime.of(9, 0)))
    }
