package woowacourse.movie.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HeadCountTest {
    @Test
    fun `인원수가 0명인 경우에 minus를 호출해도 인원수가 줄어들지 않는다`() {
        val headCount = HeadCount(0)
        val newHeadCount =headCount.minus()

        val expected = HeadCount(0)
        assertEquals(newHeadCount, expected)
    }

    @Test
    fun `인원수가 3명인 경우에 minus를 호출하면 인원수가 줄어든다`() {
        val headCount = HeadCount(3)
        val newHeadCount =headCount.minus()

        val expected = HeadCount(2)
        assertEquals(newHeadCount, expected)
    }

    @Test
    fun `plus를 호출하면 인원수가 증가한다`() {
        val headCount = HeadCount(0)
        val newHeadCount =headCount.plus()

        val expected = HeadCount(1)
        assertEquals(newHeadCount, expected)
    }

    @Test
    fun `인원수에 따라 인원수 증가, 감소가 가능한지 판단할 수 있다`() {
        val headCount = HeadCount(3)
        val headCount2 = HeadCount(0)

        assertTrue { headCount.isValid() }
        assertFalse { headCount2.isValid() }
    }
}
