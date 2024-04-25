package woowacourse.movie.domain.model.reservation.date

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import woowacourse.movie.domain.model.ScreeningInfo
import java.time.LocalDate

class ScreeningDateTimeTest {
    private lateinit var screeningInfo: ScreeningInfo

    @BeforeEach
    fun setUp() {
        val startDate = LocalDate.of(2024, 4, 1)
        val endDate = LocalDate.of(2024, 5, 28)
        screeningInfo = ScreeningInfo(startDate, endDate, 100)
    }

    @ParameterizedTest
    @CsvSource("2024,4,1", "2024,8,15", "2025,12,25")
    fun `영화 상영 시작 날짜로 기본 상영 날짜가 초기화된다`(
        year: Int,
        month: Int,
        day: Int,
    ) {
        val startDate = LocalDate.of(year, month, day)
        val endDate = LocalDate.of(year, 12, 31)
        val screeningInfo = ScreeningInfo(startDate, endDate, 100)
        val screeningDateTime = ScreeningDateTime(screeningInfo)

        assertThat(screeningDateTime.screeningDate.date).isEqualTo(LocalDate.of(year, month, day))
    }

    @ParameterizedTest
    @CsvSource("2024,4,2", "2024,4,28", "2024,5,25")
    fun `상영 날짜를 변경할 수 있다`(
        year: Int,
        month: Int,
        day: Int,
    ) {
        val screeningDateTime = ScreeningDateTime(screeningInfo)

        screeningDateTime.changeScreeningDate(year, month, day)

        assertThat(screeningDateTime.screeningDate.date).isEqualTo(LocalDate.of(year, month, day))
    }

    @ParameterizedTest
    @CsvSource("2024,3,1", "2024,5,29", "2025,4,15")
    fun `상영 날짜 범위에 속하지 않는다면 날짜는 변경되지 않는다`(
        year: Int,
        month: Int,
        day: Int,
    ) {
        val screeningDateTime = ScreeningDateTime(screeningInfo)

        screeningDateTime.changeScreeningDate(year, month, day)

        assertThat(screeningDateTime.screeningDate.date).isNotEqualTo(
            LocalDate.of(
                year,
                month,
                day,
            ),
        )
    }
}
