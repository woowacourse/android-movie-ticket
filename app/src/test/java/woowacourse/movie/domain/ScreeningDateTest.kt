package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScreeningDateTest {
    @Test
    fun `상영 종료일이 상영 시작일 이후가 아닐 경우 예외 발생`() {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val startDate: LocalDate = LocalDate.parse("2025.04.26", formatter)
        val endDate: LocalDate = LocalDate.parse("2025.04.25", formatter)

        assertThatThrownBy { ScreeningDate(startDate, endDate) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상영 종료일은 상영 시작일 이후여야 합니다")
    }
}
