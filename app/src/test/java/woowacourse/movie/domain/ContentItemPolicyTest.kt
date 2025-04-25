package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.screening.Advertisement
import woowacourse.movie.domain.screening.Advertisements
import woowacourse.movie.domain.screening.DefaultScreeningContentsPolicy
import woowacourse.movie.domain.screening.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.screening.ScreeningContent
import woowacourse.movie.domain.screening.ScreeningContentsPolicy
import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalDateTime

class ContentItemPolicyTest {
    private val harryPotterPhilosopersStone = Movie(0, "해리 포터와 마법사의 돌", 152)
    private val harryPotterChamberOfSecrets = Movie(1, "해리 포터와 비밀의 방", 162)
    private val harryPotterPrisonerOfAzkaban = Movie(2, "해리 포터와 아즈카반의 죄수", 141)
    private val harryPotterGobletOfFire = Movie(3, "해리 포터와 불의 잔", 157)

    private val fakeCurrent: LocalDateTime =
        LocalDateTime.of(2025, 4, 25, 10, 0) ?: throw DateTimeException("No Such LocalDate")

    @Test
    fun `영화 목록에 영화가 세 번 노출될 때마다 광고가 한 번 노출된다`() {
        // given
        val screenings: List<Screening> =
            listOf(
                Screening(
                    harryPotterPhilosopersStone,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                    fakeCurrent,
                ),
                Screening(
                    harryPotterChamberOfSecrets,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 28),
                    fakeCurrent,
                ),
                Screening(
                    harryPotterPrisonerOfAzkaban,
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 31),
                    fakeCurrent,
                ),
                Screening(
                    harryPotterGobletOfFire,
                    LocalDate.of(2025, 6, 1),
                    LocalDate.of(2025, 6, 30),
                    fakeCurrent,
                ),
            )
        val advertisements = Advertisements(listOf(Advertisement(0)))
        val screeningContentsPolicy: ScreeningContentsPolicy =
            DefaultScreeningContentsPolicy(screenings, advertisements)

        // when
        val screeningContents: List<ScreeningContent> = screeningContentsPolicy.screeningContents()

        // then
        assertThat(screeningContents).isEqualTo(
            listOf(
                Screening(
                    harryPotterPhilosopersStone,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 25),
                    fakeCurrent,
                ),
                Screening(
                    harryPotterChamberOfSecrets,
                    LocalDate.of(2025, 4, 1),
                    LocalDate.of(2025, 4, 28),
                    fakeCurrent,
                ),
                Screening(
                    harryPotterPrisonerOfAzkaban,
                    LocalDate.of(2025, 5, 1),
                    LocalDate.of(2025, 5, 31),
                    fakeCurrent,
                ),
                Advertisement(0),
                Screening(
                    harryPotterGobletOfFire,
                    LocalDate.of(2025, 6, 1),
                    LocalDate.of(2025, 6, 30),
                    fakeCurrent,
                ),
            ),
        )
    }
}
