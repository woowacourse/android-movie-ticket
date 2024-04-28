package woowacourse.movie.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieDateTest {
    @Test
    fun `setCurrentDate 메서드가 올바르게 동작하는지 확인한다`() {
        // Given
        val movieDate = MovieDate()
        val newDate = LocalDate.of(2024, 5, 15)

        // When
        movieDate.setCurrentDate(newDate)

        // Then
        assertEquals(newDate, movieDate.currentDate)
    }

    @Test
    fun `setCurrentTime 메서드가 올바르게 동작하는지 확인한다`() {
        // Given
        val movieDate = MovieDate()
        val newTime = LocalTime.of( 10, 30)

        // When
        movieDate.setCurrentTime(newTime)

        // Then
        assertEquals(newTime, movieDate.currentTime)
    }
}
