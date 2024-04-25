package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.data.MovieDao

class TheaterTest {
    private val movieDao = MovieDao()

    @Test
    fun `영화의 상영시간을 list로 가져올 수 있다`() {
        // given
        val movie = movieDao.find(0)
        val theater = Theater(movie)

        // when
        val times = theater.screenTimes()

        // then
        assertThat(times).isNotEmpty
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


