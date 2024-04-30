package woowacourse.movie.domain.model

import java.time.LocalDate

data class Screen(
    val id: Int,
    val movie: Movie,
    val dateRange: DateRange = DateRange(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 12, 31)),
) {
    companion object {
        val NULL =
            Screen(
                id = -1,
                movie = Movie.NULL,
            )
    }
}
