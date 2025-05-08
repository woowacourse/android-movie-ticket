package woowacourse.movie.domain.reservation

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ShowtimePolicyTest {
    private lateinit var showtimePolicy: ShowtimePolicy
    private val holiday = LocalDate.of(2025, 4, 27)
    private val workday = LocalDate.of(2025, 4, 28)

    @BeforeEach
    fun setUp() {
        showtimePolicy = DefaultShowtimePolicy()
    }

    @Test
    fun `주말에는 오전 9시부터 자정(24시)까지 두 시간 간격으로 상영한다`() {
        // when
        val showtimes: List<LocalTime> =
            showtimePolicy.showtimes(holiday, LocalDateTime.of(holiday, LocalTime.of(8, 0)))

        // then
        assertThat(showtimes).isEqualTo(
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            ),
        )
    }

    @Test
    fun `평일에는 오전 10시부터 자정(24시)까지 두 시간 간격으로 상영한다`() {
        // when
        val showtimes: List<LocalTime> =
            showtimePolicy.showtimes(workday, LocalDateTime.of(holiday, LocalTime.of(8, 0)))

        // then
        assertThat(showtimes).isEqualTo(
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            ),
        )
    }
}
