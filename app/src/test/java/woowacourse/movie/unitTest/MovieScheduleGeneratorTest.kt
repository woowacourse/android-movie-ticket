package woowacourse.movie.unitTest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieScheduleGenerator
import java.time.LocalDate
import java.time.LocalTime

class MovieScheduleGeneratorTest {
    @Test
    fun `시작날짜와_끝_날짜를_알면_중간_날짜들을_알_수_있다`() {
        assertThat(
            MovieScheduleGenerator.generateScreeningDates(
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 3),
            ),
        ).isEqualTo(
            listOf(
                LocalDate.of(2024, 4, 1),
                LocalDate.of(2024, 4, 2),
                LocalDate.of(2024, 4, 3),
            ),
        )
    }

    @Test
    fun `날짜의_평일_주말_여부에_따라_리턴되는_시작시간이_달라진다`() {
        assertThat(
            MovieScheduleGenerator.generateScreeningTimesFor(
                LocalDate.of(
                    2024,
                    4,
                    20,
                ),
            )[0],
        ).isEqualTo(
            LocalTime.of(9, 0),
        )
        assertThat(
            MovieScheduleGenerator.generateScreeningTimesFor(
                LocalDate.of(
                    2024,
                    4,
                    22,
                ),
            )[0],
        ).isEqualTo(LocalTime.of(10, 0))
    }
}
