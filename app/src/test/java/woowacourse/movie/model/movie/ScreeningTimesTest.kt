package woowacourse.movie.model.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.db.screening.ScreeningDatabase

class ScreeningTimesTest {
    private val screeningTimes: ScreeningTimes = ScreeningDatabase.movies[0].screeningTimes

    @Test
    fun `사용자가 선택한 날짜가 평일이라면 평일 상영 시간표를 반환한다`() {
        val weekday = "2024-03-01"
        val actual = screeningTimes.loadScheduleByDateType(weekday)

        assertThat(actual).isEqualTo(screeningTimes.weekDay)
    }

    @Test
    fun `사용자가 선택한 날짜가 주말이라면 주말 상영 시간표를 반환한다`() {
        val weekend = "2024-03-02"
        val actual = screeningTimes.loadScheduleByDateType(weekend)

        assertThat(actual).isEqualTo(screeningTimes.weekEnd)
    }
}
