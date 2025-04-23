package woowacourse.movie

import woowacourse.movie.model.Movie
import java.time.LocalDate

val MOVIE_VALUE: Movie =
    Movie(
        "라라랜드",
        R.drawable.lalaland,
        LocalDate.of(2025, 4, 1),
        LocalDate.of(2025, 4, 20),
        120,
    )
