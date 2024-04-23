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
        var movieReservationCount = MovieReservationCount()

        movieReservationCount = movieReservationCount.inc()

        assertThat(movieReservationCount.count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행하면 1 줄어든다`() {
        var movieReservationCount = MovieReservationCount()

        movieReservationCount = movieReservationCount.inc()
        movieReservationCount = movieReservationCount.inc()
        movieReservationCount = movieReservationCount.dec()

        assertThat(movieReservationCount.count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행해도 최소 예매 인원수의 1 미만으로 내려가지 않는다`() {
        var movieReservationCount = MovieReservationCount()

        movieReservationCount = movieReservationCount.dec()

        assertThat(movieReservationCount.count).isEqualTo(1)
    }

    @Test
    fun `plus를 진행해도 최대 예매 인원수의 10 초과로 올라가지 않는다`() {
        var movieReservationCount = MovieReservationCount(10)

        movieReservationCount = movieReservationCount.inc()

        assertThat(movieReservationCount.count).isEqualTo(10)
    }
}
