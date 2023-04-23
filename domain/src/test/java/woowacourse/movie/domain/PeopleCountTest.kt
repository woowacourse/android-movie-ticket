package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PeopleCountTest {
    @Test
    fun `인원수가 1 감소한다`() {
        val count = PeopleCount(5)
        assertThat(count.minusCount().value).isEqualTo(4)
    }

    @Test
    fun `인원수가 1이면 감소하지 않는다`() {
        val count = PeopleCount(1)
        assertThat(count.minusCount().value).isEqualTo(1)
    }

    @Test
    fun `인원수가 1 증가한다`() {
        val count = PeopleCount(5)
        assertThat(count.plusCount().value).isEqualTo(6)
    }
}
