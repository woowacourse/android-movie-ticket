package woowacourse.movie

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieTicket
import woowacourse.movie.model.MovieTime
import java.time.LocalDate

val MOVIE: Movie =
    Movie(
        "라라랜드",
        R.drawable.lalaland,
        LocalDate.of(2025, 4, 1),
        LocalDate.of(2025, 5, 30),
        120,
    )

val MOVIE_TICKET: MovieTicket =
    MovieTicket(
        "라라랜드",
        LocalDate.of(2025, 4, 1),
        MovieTime(),
        2,
    )

val fakeContext: Context = ApplicationProvider.getApplicationContext()
