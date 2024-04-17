package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountTest {
    @Test
    fun `amount의 값이 2이상일 때, sub함수 호출하면 amount의 값이 1감소한다`() {
        val count = Count(2)

        count.sub()

        assertThat(count.amount).isEqualTo(1)
    }

    @Test
    fun `amount의 값이 2미만일 때, sub함수 호출하면 amount의 값은 감소하지 않는다`() {
        val count = Count(1)

        count.sub()

        assertThat(count.amount).isEqualTo(1)
    }

    @Test
    fun `add함수 호출하면 amount의 값이 1증가한다`() {
        val count = Count(2)

        count.add()

        assertThat(count.amount).isEqualTo(3)
    }
}
