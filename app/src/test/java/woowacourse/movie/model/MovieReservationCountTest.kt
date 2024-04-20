package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieReservationCountTest {
    @Test
    fun `기본값은 1이다`() {
        assertThat(MovieReservationCount().count).isEqualTo(1)
    }

    @Test
    fun `plus를 진행하면 1이 늘어난다`() {
        val movieReservationCount = MovieReservationCount()
        movieReservationCount.plus()
        assertThat(movieReservationCount.count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행하면 1 줄어든다`() {
        val movieReservationCount = MovieReservationCount()
        movieReservationCount.plus()
        movieReservationCount.plus()
        movieReservationCount.minus()
        assertThat(movieReservationCount.count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행해도 1 미만으로 내려가지 않는다`() {
        val movieReservationCount = MovieReservationCount()
        movieReservationCount.minus()
        movieReservationCount.minus()
        movieReservationCount.minus()
        assertThat(movieReservationCount.count).isEqualTo(1)
    }
}
