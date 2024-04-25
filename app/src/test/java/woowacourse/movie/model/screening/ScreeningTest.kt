package woowacourse.movie.model.screening

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ScreeningTest {
    @Test
    fun `상영_정보에_따른_올바른_기간들을_반환한다`() {
        // given
        val screening =
            Screening.of(
                0,
                1,
                DatePeriod(
                    startDate = LocalDate.of(2024, 3, 1),
                    endDate = LocalDate.of(2024, 3, 15),
                    dateSpan = 1,
                ),
            )

        // then
        assertEquals(
            listOf(
                "2024-03-01",
                "2024-03-02",
                "2024-03-03",
                "2024-03-04",
                "2024-03-05",
                "2024-03-06",
                "2024-03-07",
                "2024-03-08",
                "2024-03-09",
                "2024-03-10",
                "2024-03-11",
                "2024-03-12",
                "2024-03-13",
                "2024-03-14",
                "2024-03-15",
            ),
            screening.dates,
        )
    }
}
