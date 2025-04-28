package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.movietime.ScreeningTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningTimeTest {
    private lateinit var currentDateTime: LocalDateTime

    @BeforeEach
    fun setup() {
        currentDateTime = LocalDateTime.of(2025, 4, 22, 17, 23)
    }

    @Test
    fun `영화 스케줄은 평일 상영가능한 시간 리스트를 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 23)
        val movieSchedule = ScreeningTime(selectedDate, currentDateTime)
        // when
        val actual = movieSchedule.selectableTimes()
        val expected =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            )
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 스케줄은 주말 상영가능한 시간 리스트를 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 19)
        val movieSchedule = ScreeningTime(selectedDate, currentDateTime)
        // when
        val actual = movieSchedule.selectableTimes()
        val expected =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `영화 스케줄은 현재 시간 기준 이후로 시간 리스트를 반환한다`() {
        // given
        val selectedDate = LocalDate.of(2025, 4, 22)
        val movieSchedule = ScreeningTime(selectedDate, currentDateTime)
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
