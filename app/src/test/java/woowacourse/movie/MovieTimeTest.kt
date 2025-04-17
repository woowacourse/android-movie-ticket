package woowacourse.movie

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.model.MovieTime
import java.time.LocalDate
import java.time.LocalDateTime

class MovieTimeTest {
    private lateinit var movieTime: MovieTime

    @BeforeEach
    fun setup() {
        movieTime = MovieTime()
    }

    @Test
    fun `오늘 기준 선택한 날짜가 미래이고, 주말일 경우 오전 9시부터 자정까지 2시간 간격의 타임 테이블이 반환된다`() {
        // given:
        val now = LocalDateTime.of(2025, 4, 17, 11, 24)
        val selectedDate = LocalDate.of(2025, 4, 19) // 주말

        // when:
        val timeTable: List<Int> = movieTime.getTimeTable(now, selectedDate)

        // then:
        assertThat(timeTable).isEqualTo(listOf(9, 11, 13, 15, 17, 19, 21, 23))
    }

    @Test
    fun `오늘 기준 선택한 날짜가 미래이고, 평일일 경우 오전 10시부터 자정까지 2시간 간격의 타임 테이블이 반환된다`() {
        // given:
        val now = LocalDateTime.of(2025, 4, 17, 11, 24)
        val selectedDate = LocalDate.of(2025, 4, 21) // 평일

        // when:
        val timeTable: List<Int> = movieTime.getTimeTable(now, selectedDate)

        // then:
        assertThat(timeTable).isEqualTo(listOf(10, 12, 14, 16, 18, 20, 22, 24))
    }

    @Test
    fun `오늘 기준 선택한 날짜가 오늘이고, 주말일 경우 오전 9시 이후 2시간 간격의 타임 중 현재 시간 보다 뒤의 타임 테이블이 반환된다`() {
        // given:
        val now = LocalDateTime.of(2025, 4, 19, 11, 24)
        val selectedDate = LocalDate.of(2025, 4, 19) // 주말

        // when:
        val timeTable: List<Int> = movieTime.getTimeTable(now, selectedDate)

        // then:
        assertThat(timeTable).isEqualTo(listOf(13, 15, 17, 19, 21, 23))
    }

    @Test
    fun `오늘 기준 선택한 날짜가 오늘이고, 평일일 경우 오전 10시 이후 2시간 간격의 타임 중 현재 시간 보다 뒤의 타임 테이블이 반환된다`() {
        // given:
        val now = LocalDateTime.of(2025, 4, 17, 15, 30)
        val selectedDate = LocalDate.of(2025, 4, 17) // 평일

        // when:
        val timeTable: List<Int> = movieTime.getTimeTable(now, selectedDate)

        // then:
        assertThat(timeTable).isEqualTo(listOf(16, 18, 20, 22, 24))
    }
}
