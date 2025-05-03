package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.fixture.A1
import woowacourse.movie.fixture.B1
import woowacourse.movie.fixture.C1
import woowacourse.movie.fixture.D1
import woowacourse.movie.fixture.E1

class SeatTest {
    @Test
    fun `1,2행의 좌석은 10,000원이다`() {
        assertThat(A1.price).isEqualTo(10_000)
        assertThat(B1.price).isEqualTo(10_000)
    }

    @Test
    fun `3,4행의 좌석은 15,000원이다`() {
        assertThat(C1.price).isEqualTo(15_000)
        assertThat(D1.price).isEqualTo(15_000)
    }

    @Test
    fun `5행의 좌석은 12,000원이다`() {
        assertThat(E1.price).isEqualTo(12_000)
    }
}
