package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalTime

class MovieTimeTest {
    @Test
    fun `평일 영화 상영시간은 10 ~ 24(00)시 사이이다`() {
        val movieTime = MovieTime(false)

        val expect =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
                LocalTime.of(0, 0),
            )
        assertThat(movieTime.generateTimes()).isEqualTo(expect)
    }

    @Test
    fun `주말 영화 상영시간은 09 ~ 23시 사이이다`() {
        val movieTime = MovieTime(true)

        val expect =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )
        assertThat(movieTime.generateTimes()).isEqualTo(expect)
    }
}
