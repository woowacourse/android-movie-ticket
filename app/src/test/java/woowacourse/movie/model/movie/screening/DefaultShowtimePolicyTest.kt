package woowacourse.movie.model.movie.screening

import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDate
import java.time.LocalTime
import kotlin.test.Test

class DefaultShowtimePolicyTest {
    private val policy = DefaultShowtimePolicy()

    @Test
    fun `평일에는 10시부터 2시간 간격으로 상영 시간이 제공된다`() {
        // given
        val mondayDate = LocalDate.of(2025, 4, 21) // 월요일

        // when
        val actualTimes = policy.showtimes(mondayDate)

        // then
        val expectedTimes =
            (10 until 24 step 2).map { hour ->
                LocalTime.of(hour, 0)
            }
        assertThat(actualTimes).isEqualTo(expectedTimes)
    }

    @Test
    fun `토요일에는 9시부터 2시간 간격으로 상영 시간이 제공된다`() {
        // given
        val saturdayDate = LocalDate.of(2025, 4, 19) // 토요일

        // when
        val actualTimes = policy.showtimes(saturdayDate)

        // then
        val expectedTimes =
            (9 until 24 step 2).map { hour ->
                LocalTime.of(hour, 0)
            }
        assertThat(actualTimes).isEqualTo(expectedTimes)
    }

    @Test
    fun `일요일에는 9시부터 2시간 간격으로 상영 시간이 제공된다`() {
        // given
        val sundayDate = LocalDate.of(2025, 4, 20) // 일요일

        // when
        val actualTimes = policy.showtimes(sundayDate)

        // then
        val expectedTimes =
            (9 until 24 step 2).map { hour ->
                LocalTime.of(hour, 0)
            }
        assertThat(actualTimes).isEqualTo(expectedTimes)
    }
}
