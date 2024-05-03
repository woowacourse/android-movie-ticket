package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieDao
import java.time.LocalDate

class TheaterTest {
    private val movieDao = MovieDao()

    @Test
    fun `영화의 상영시간을 list로 가져올 수 있다`() {
        // given
        val movie = movieDao.find(0)
        val theater = Theater(movie)

        // when
        val times = theater.screenTimes(movie.screenDate[0])

        // then
        assertThat(times).isNotEmpty
    }

    @Test
    fun `상영일이 주말인 경우 영화 상영시간은 9시부터 시작한다`() {
        // given
        val movie = movieDao.find(0)
        val theater = Theater(movie)

        // when
        val times = theater.screenTimes(LocalDate.of(2024, 4, 27))

        // then
        assertThat(times[0].hour).isEqualTo(9)
    }

    @Test
    fun `상영일이 평일인 경우 영화 상영시간은 10시부터 시작한다`() {
        // given
        val movie = movieDao.find(0)
        val theater = Theater(movie)

        // when
        val times = theater.screenTimes(LocalDate.of(2024, 4, 25))

        // then
        assertThat(times[0].hour).isEqualTo(10)
    }

    @Test
    fun `영화의 상영일을 list로 가져올 수 있다`() {
        // given
        val movie = movieDao.find(0)
        val theater = Theater(movie)

        // when
        val screenDates = theater.screenDates()

        // then
        assertThat(screenDates).isNotEmpty
        assertThat(screenDates).contains(movie.screenDate[0])
        assertThat(screenDates).contains(movie.screenDate[1])
    }
}
