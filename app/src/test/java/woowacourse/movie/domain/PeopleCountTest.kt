package woowacourse.movie.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.PeopleCount

class PeopleCountTest {
    @Test
    fun `인원수가 한명에서 두명으로 증가한다`() {
        // given
        val peopleCount = PeopleCount(1)

        // when
        val expected = peopleCount.increase().value

        // then
        assertEquals(expected, 2)
    }

    @Test
    fun `인원수가 두명에서 한명으로 감소한다`() {
        // given
        val peopleCount = PeopleCount(2)

        // when
        val expected = peopleCount.decrease().value

        // then
        assertEquals(expected, 1)
    }

    @Test
    fun `인원수가 한명보다 적으면 거짓을 반환한다`() {
        // given
        val peopleCount = PeopleCount(1)

        // when
        val expected = peopleCount.decrease().value

        // then
        assertEquals(expected, PeopleCount(1).value)
    }
}
