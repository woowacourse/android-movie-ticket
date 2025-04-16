package woowacourse.movie.domain.study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateTest {
    @Test
    fun `다음달로 넘어가는지 테스트`() {
        // given
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val expected: LocalDate = LocalDate.parse("2025.05.01", formatter)

        // when
        val actual: LocalDate = LocalDate.parse("2025.04.30", formatter).plusDays(1)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `다음 연도로 넘어가는지 테스트`() {
        // given
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val expected: LocalDate = LocalDate.parse("2026.01.01", formatter)

        // when
        val actual: LocalDate = LocalDate.parse("2025.12.31", formatter).plusDays(1)

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
