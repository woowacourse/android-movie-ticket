package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CountTest {
    @Test
    fun `초기에_설정한_티켓_개수에_맞는_수를_갖는다`() {
        val count = Count(1)
        assertThat(count.value).isEqualTo(1)
    }

    @Test
    fun `초기에_설정한_티켓_개수가_가능한_최소_개수_미만이면_예외를_발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Count(0)
        }
    }

    @Test
    fun `티켓_수를_증가시키는_동작을_수행하면_티켓_수가_증가한다`() {
        val count = Count(1)
        count.increase()
        assertThat(count.value).isEqualTo(2)
    }

    @Test
    fun `티켓_수가_초기값인_경우_감소시키는_동작_수행_시_감소하지_않는다`() {
        val count = Count(1)
        count.decrease()
        assertThat(count.value).isEqualTo(1)
    }

    @Test
    fun `티켓_수가_초기값이_아닌_경우_감소시키는_동작_수행_시_감소한다`() {
        val count = Count(2)
        count.decrease()
        assertThat(count.value).isEqualTo(1)
    }
}
