package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.util.MovieScheduleUtils

class MovieScheduleUtilsTest {
    @Test
    fun `시작날짜와_끝_날짜를_알면_중간_날짜들을_알_수_있다`() {
        assertThat(
            MovieScheduleUtils.generateScreeningDates(
                "2024.4.1",
                "2024.4.3",
            ),
        ).isEqualTo(
            listOf(
                "2024.4.1",
                "2024.4.2",
                "2024.4.3",
            ),
        )
    }

    @Test
    fun `날짜의_평일_주말_여부에_따라_리턴되는_시작시간이_달라진다`() {
        assertThat(MovieScheduleUtils.generateScreeningTimesFor("2024.4.20")[0]).isEqualTo("09:00")
        assertThat(MovieScheduleUtils.generateScreeningTimesFor("2024.4.22")[0]).isEqualTo("10:00")
    }
}
