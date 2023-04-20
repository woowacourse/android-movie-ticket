package domain.movieTimePolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class WeekdayMovieTimeTest {
    @Test
    fun `평일이면 영화 상영시간은 10시부터 24시까지 2시간 간격이다`() {
        // given
        val date = LocalDate.of(2023, 4, 13)
        // when
        val actual = domain.movieTimePolicy.WeekdayMovieTime.generateTime(date)
        // then
        val expected = listOf<LocalTime>(
            LocalTime.of(10, 0),
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            LocalTime.of(16, 0),
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일이 아니라면 null을 반환한다`() {
        // given
        val date = LocalDate.of(2023, 4, 15)
        // when
        val actual = WeekdayMovieTime.generateTime(date)
        // then
        assertThat(actual).isNull()
    }
}
