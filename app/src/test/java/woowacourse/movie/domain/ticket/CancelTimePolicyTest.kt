package woowacourse.movie.domain.ticket

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CancelTimePolicyTest {
    private lateinit var cancelTimePolicy: CancelTimePolicy

    @BeforeEach
    fun setUp() {
        cancelTimePolicy = DefaultCancelTimePolicy
    }

    @Test
    fun `영화 상영 시작 시간 15분 전까지 취소가 가능하다`() {
        // when
        val cancelableMinutes: Int = cancelTimePolicy.cancelableMinutes

        // then
        assertThat(cancelableMinutes).isEqualTo(15)
    }
}
