package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ScreeningScheduleTest {
    private lateinit var screeningSchedule: ScreeningSchedule

    @BeforeEach
    fun setup() {
        // given
        screeningSchedule = ScreeningSchedule()
    }

    @Test
    fun `영화 상영 일정을 생성한다`() {
        // when
        screeningSchedule = ScreeningSchedule(LocalDate.of(2024, 4, 1), LocalTime.of(10, 0))

        // then
        assertNotNull(screeningSchedule)
    }

    @Test
    fun `영화 상영 일정을 수정한다`() {
        // when
        screeningSchedule.updateDate(LocalDate.of(2024, 4, 1))
        screeningSchedule.updateTime(LocalTime.of(10, 0))

        // then
        assertThat(screeningSchedule.dateTime).isEqualTo(
            LocalDateTime.of(
                LocalDate.of(2024, 4, 1),
                LocalTime.of(10, 0),
            ),
        )
    }

    @Test
    fun `영화 상영이 주말인지 확인한다`() {
        // when, then
        assertThat(
            screeningSchedule.times(
                LocalDate.of(
                    2024,
                    4,
                    27,
                ),
            ),
        ).isEqualTo(ScreeningSchedule.weekendTimes)
    }

    @Test
    fun `영화 상영이 평일인지 확인한다`() {
        // when, then
        assertThat(
            screeningSchedule.times(
                LocalDate.of(
                    2024,
                    4,
                    26,
                ),
            ),
        ).isEqualTo(ScreeningSchedule.weekdayTimes)
    }

    @Test
    fun `영화 상영 일정이 유효한지 확인한다`() {
        // when
        screeningSchedule.updateDate(LocalDate.of(2024, 4, 1))
        screeningSchedule.updateTime(LocalTime.of(10, 0))

        // then
        assertThat(screeningSchedule.isValidate()).isTrue
    }

    @Test
    fun `영화 상영 일정이 유효하지 않은지 확인한다`() {
        // when
        screeningSchedule.updateDate(LocalDate.of(2024, 4, 1))

        // then
        assertThat(screeningSchedule.isValidate()).isFalse
    }
}
