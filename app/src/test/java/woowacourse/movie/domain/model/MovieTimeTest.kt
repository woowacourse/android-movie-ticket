package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieTimeTest {
    @Test
    fun `주중에 대한 상영 시간 리스트를 반환한다`() {
        // given
        val expectedTimes =
            listOf(
                MovieTime(10, 0),
                MovieTime(12, 0),
                MovieTime(14, 0),
                MovieTime(16, 0),
                MovieTime(18, 0),
                MovieTime(20, 0),
                MovieTime(22, 0),
                MovieTime(0, 0),
            )

        // when
        val actual = MovieTime.getMovieTimes(DateType.WEEKDAY)

        // then
        assertThat(actual).isEqualTo(expectedTimes)
    }

    @Test
    fun `주말에 대한 상영 시간 리스트를 반환한다`() {
        // given
        val expectedTimes =
            listOf(
                MovieTime(9, 0),
                MovieTime(11, 0),
                MovieTime(13, 0),
                MovieTime(15, 0),
                MovieTime(17, 0),
                MovieTime(19, 0),
                MovieTime(21, 0),
                MovieTime(23, 0),
            )

        // when
        val actual = MovieTime.getMovieTimes(DateType.WEEKEND)

        // then
        assertThat(actual).isEqualTo(expectedTimes)
    }
}
