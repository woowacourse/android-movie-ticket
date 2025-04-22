package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalTime

class MovieTimeTest {
    @Test
    fun `from는 문자열을 LocalTime으로 파싱해 MovieTime으로 변환한다`() {
        // given
        val timeString = "14:30"

        // when
        val movieTime = MovieTime.from(timeString)

        // then
        assertThat(movieTime.value).isEqualTo(LocalTime.of(14, 30))
    }

    @Test
    fun `주중에 대한 상영 시간 리스트를 반환한다`() {
        // given
        val expectedTimes =
            listOf(
                MovieTime(LocalTime.of(10, 0)),
                MovieTime(LocalTime.of(12, 0)),
                MovieTime(LocalTime.of(14, 0)),
                MovieTime(LocalTime.of(16, 0)),
                MovieTime(LocalTime.of(18, 0)),
                MovieTime(LocalTime.of(20, 0)),
                MovieTime(LocalTime.of(22, 0)),
                MovieTime(LocalTime.of(0, 0)),
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
                MovieTime(LocalTime.of(9, 0)),
                MovieTime(LocalTime.of(11, 0)),
                MovieTime(LocalTime.of(13, 0)),
                MovieTime(LocalTime.of(15, 0)),
                MovieTime(LocalTime.of(17, 0)),
                MovieTime(LocalTime.of(19, 0)),
                MovieTime(LocalTime.of(21, 0)),
                MovieTime(LocalTime.of(23, 0)),
            )

        // when
        val actual = MovieTime.getMovieTimes(DateType.WEEKEND)

        // then
        assertThat(actual).isEqualTo(expectedTimes)
    }
}
