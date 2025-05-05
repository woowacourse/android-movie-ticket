package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieScheduleGenerator
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MovieScheduleUtilsTest {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    @Test
    fun `시작날짜와_끝_날짜를_알면_중간_날짜들을_알_수_있다`() {
        assertThat(
            MovieScheduleGenerator.generateScreeningDates(
                LocalDate.parse("2024.4.1", dateFormatter),
                LocalDate.parse("2024.4.3", dateFormatter),
            ),
        ).isEqualTo(
            listOf(
                LocalDate.parse("2024.4.1", dateFormatter),
                LocalDate.parse("2024.4.2", dateFormatter),
                LocalDate.parse("2024.4.3", dateFormatter),
            ),
        )
    }

    @Test
    fun `날짜의_평일_주말_여부에_따라_리턴되는_시작시간이_달라진다`() {
        assertThat(MovieScheduleGenerator.generateScreeningTimesFor(LocalDate.parse("2024.4.20", dateFormatter))[0])
            .isEqualTo(LocalTime.parse("09:00", timeFormatter))
        assertThat(MovieScheduleGenerator.generateScreeningTimesFor(LocalDate.parse("2024.4.22", dateFormatter))[0])
            .isEqualTo(LocalTime.parse("10:00", timeFormatter))
    }
}
