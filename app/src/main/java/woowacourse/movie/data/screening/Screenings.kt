package woowacourse.movie.data.screening

import woowacourse.movie.domain.screening.Screening
import java.time.LocalDate

interface Screenings {
    val value: List<Screening>
}

class LocalScreenings : Screenings {
    private val movies = Movies()
    override val value: List<Screening> =
        listOf(
            Screening(
                movies.harryPotterPhilosopersStone,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            Screening(
                movies.harryPotterChamberOfSecrets,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 28),
            ),
            Screening(
                movies.harryPotterPrisonerOfAzkaban,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 31),
            ),
            Screening(
                movies.harryPotterGobletOfFire,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 30),
            ),
            Screening(
                movies.harryPotterPhilosopersStone,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 25),
            ),
            Screening(
                movies.harryPotterChamberOfSecrets,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 28),
            ),
            Screening(
                movies.harryPotterPrisonerOfAzkaban,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 30),
            ),
            Screening(
                movies.harryPotterGobletOfFire,
                LocalDate.of(2025, 7, 1),
                LocalDate.of(2025, 7, 31),
            ),
        )
}

class FakeScreenings(
    override val value: List<Screening>,
) : Screenings
