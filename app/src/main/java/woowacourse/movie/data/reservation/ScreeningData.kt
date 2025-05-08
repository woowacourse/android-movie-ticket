package woowacourse.movie.data.reservation

import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate

interface ScreeningData {
    val value: List<Screening>
}

class LocalScreeningData : ScreeningData {
    private val movieData = MovieData()
    override val value: List<Screening> =
        listOf(
            Screening(
                movieData.harryPotterPhilosopersStone,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            Screening(
                movieData.harryPotterChamberOfSecrets,
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 28),
            ),
            Screening(
                movieData.harryPotterPrisonerOfAzkaban,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 31),
            ),
            Screening(
                movieData.harryPotterGobletOfFire,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 30),
            ),
            Screening(
                movieData.harryPotterPhilosopersStone,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 25),
            ),
            Screening(
                movieData.harryPotterChamberOfSecrets,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 28),
            ),
            Screening(
                movieData.harryPotterPrisonerOfAzkaban,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 30),
            ),
            Screening(
                movieData.harryPotterGobletOfFire,
                LocalDate.of(2025, 7, 1),
                LocalDate.of(2025, 7, 31),
            ),
        ).filter { it.availableDates.isNotEmpty() }
}

class FakeScreeningData(
    override val value: List<Screening>,
) : ScreeningData
