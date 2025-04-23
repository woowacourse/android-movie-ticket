package woowacourse.movie.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HeadcountTest {
    @Test
    fun `인원 수는 증가할 수 있다`() {
        val headcount = Headcount(1)
        headcount.increase()

        val actual = headcount.count

        val expected = 2

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `인원 수는 최대 인원보다 증가하지 않는다`() {
        val headcount = Headcount(100)
        headcount.increase()

        val actual = headcount.count

        val expected = 100

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `인원 수는 감소할 수 있다`() {
        val headcount = Headcount(2)
        headcount.decrease()

        val actual = headcount.count

        val expected = 1

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `인원 수는 최소 인원보다 감소하지 않는다`() {
        val headcount = Headcount(1)
        headcount.decrease()

        val actual = headcount.count

        val expected = 1

        assertThat(actual).isEqualTo(expected)
    }
}
