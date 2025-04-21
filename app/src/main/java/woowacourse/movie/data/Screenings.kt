package woowacourse.movie.data

import woowacourse.movie.view.model.Screening
import java.time.LocalDate

class Screenings {
    private val movies = Movies()
    val value: List<Screening> =
        listOf(
            Screening(
                movies.harryPotter,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            Screening(
                movies.harryPotter,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 25),
            ),
            Screening(
                movies.harryPotter,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 25),
            ),
        )
}
