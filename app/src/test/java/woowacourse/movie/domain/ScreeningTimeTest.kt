package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeTest {
    @Test
    fun `영화 스케줄은 평일 상영가능한 시간 리스트를 반환한다`() {
        // given
        val currentTime = LocalDateTime.of(2025, 4, 23, 17, 20)
        val movieSchedule = ScreeningTime(currentTime)
        // when
        val actual = movieSchedule.selectableTimes()
        val expected =
            listOf(
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 스케줄은 주말 상영가능한 시간 리스트를 반환한다`() {
        // given
        val currentTime = LocalDateTime.of(2025, 4, 19, 17, 20)
        val movieSchedule = ScreeningTime(currentTime)
        // when
        val actual = movieSchedule.selectableTimes()
        val expected =
            listOf(
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            )
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
