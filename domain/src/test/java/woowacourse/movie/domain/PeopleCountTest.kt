package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PeopleCountTest {
    @Test
    fun `인원수가 1 감소한다`() {
        val count = PeopleCount(5)
        assertEquals(4, count.minusCount().count)
    }

    @Test
    fun `인원수가 1이면 감소하지 않는다`() {
        val count = PeopleCount(1)
        assertEquals(1, count.minusCount().count)
    }

    @Test
    fun `인원수가 1 증가한다`() {
        val count = PeopleCount(5)
        assertEquals(6, count.plusCount().count)
    }
}
