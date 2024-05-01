package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.ReservationCount

class ReservationCountTest {
    @Test
    fun `기본값은 1이다`() {
        assertThat(ReservationCount().count).isEqualTo(1)
    }

    @Test
    fun `plus를 진행하면 1이 늘어난다`() {
        val reservationCount = ReservationCount()
        assertThat(reservationCount.plus(1).count).isEqualTo(2)
    }

    @Test
    fun `minus를 진행하면 1 줄어든다`() {
        val reservationCount = ReservationCount()

        assertThat(
            reservationCount
                .plus(1)
                .plus(1)
                .minus(1).count,
        ).isEqualTo(2)
    }

    @Test
    fun `minus를 진행해도 1 미만으로 내려가지 않는다`() {
        val reservationCount = ReservationCount()
        assertThat(
            reservationCount
                .minus(1)
                .minus(1)
                .minus(1).count,
        ).isEqualTo(1)
    }
}
