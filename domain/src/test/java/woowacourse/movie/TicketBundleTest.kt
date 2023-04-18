package woowacourse.movie

import com.woowacourse.domain.TicketBundle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TicketBundleTest {
    @Test
    fun `티켓 가격의 총 합을 구한다`() {
        val actual = TicketBundle.create(3).calculateTotalPrice(
            "2023-04-10",
            "13:00"
        )
        assertThat(actual).isEqualTo(35100)
    }
}
