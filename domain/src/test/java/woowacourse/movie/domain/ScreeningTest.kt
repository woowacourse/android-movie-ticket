package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class ScreeningTest {

    @Test
    fun `상영의 아이디가 설정된 상태에서 아이디를 다시 변경하려하면 변경되지 않는다`() {
        val screening = Screening(
            ScreeningDateRange(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 31)),
            MovieHouse(1L, setOf()),
            Movie(1L, "제목", Minute(152), "요약")
        )
        screening.id = 1L

        screening.id = 2L

        assertThat(screening.id).isEqualTo(1L)
    }
}
