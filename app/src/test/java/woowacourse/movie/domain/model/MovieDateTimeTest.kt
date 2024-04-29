package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalTime

class MovieDateTimeTest {
    @Test
    fun `현재 날짜를 지정하는 메서드가 올바르게 동작하는지 확인한다`() {
        // Given
        val movieDateTime = MovieDateTime()
        val newDate = LocalDate.of(2024, 5, 15)

        // When
        movieDateTime.setCurrentDate(newDate)

        // Then
        assertEquals(newDate, movieDateTime.currentDate)
    }

    @Test
    fun `현재 시간을 지정하는 메서드가 올바르게 동작하는지 확인한다`() {
        // Given
        val movieDateTime = MovieDateTime()
        val newTime = LocalTime.of( 10, 30)

        // When
        movieDateTime.setCurrentTime(newTime)

        // Then
        assertEquals(newTime, movieDateTime.currentTime)
    }
}
