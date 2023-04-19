package domain.movieTimePolicy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class WeekendMovieTimeTest {
    @Test
    fun `주말이면 영화 상영시간은 9시부터 24시까지 2시간 간격이다`() {
        // given
        val date = LocalDate.of(2023, 4, 15)
        // when
        val actual = WeekendMovieTime.generateTime(date)
        // then
        val expected = listOf<LocalTime>(
            LocalTime.of(9, 0),
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            LocalTime.of(21, 0),
            LocalTime.of(23, 0),
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말이 아니라면 null을 반환한다`() {
        // given
        val date = LocalDate.of(2023, 4, 13)
        // when
        val actual = WeekendMovieTime.generateTime(date)
        // then
        assertThat(actual).isEqualTo(null)
    }
}
