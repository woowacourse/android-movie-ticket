package woowacourse.movie.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalTime

class ScreeningTimeTest {
    @Test
    fun `영화 상영 시간 범위는 오전 9시부터 자정까지다`() {
        assertThrows<IllegalArgumentException> { ScreeningTime(LocalTime.MIN) }
    }
}
