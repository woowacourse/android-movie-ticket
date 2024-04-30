package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieCountTest {
    @Test
    fun `기본값은 1이다`() {
        assertThat(MovieCount().count).isEqualTo(1)
    }

    @Test
    fun `plus를 진행하면 1이 늘어난다`() {
        var movieCount = MovieCount()

        movieCount = movieCount.inc()

        assertThat(movieCount.count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행하면 1 줄어든다`() {
        var movieCount = MovieCount()

        movieCount = movieCount.inc()
        movieCount = movieCount.inc()
        movieCount = movieCount.dec()

        assertThat(movieCount.count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행해도 최소 예매 인원수의 1 미만으로 내려가지 않는다`() {
        var movieCount = MovieCount()

        movieCount = movieCount.dec()

        assertThat(movieCount.count).isEqualTo(1)
    }

    @Test
    fun `plus를 진행해도 최대 예매 인원수의 10 초과로 올라가지 않는다`() {
        var movieCount = MovieCount(10)

        movieCount = movieCount.inc()

        assertThat(movieCount.count).isEqualTo(10)
    }
}
