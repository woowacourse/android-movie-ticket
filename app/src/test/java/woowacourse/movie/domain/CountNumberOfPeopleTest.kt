package woowacourse.movie.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CountNumberOfPeopleTest {

    @Test
    fun `사람 수를 한 명 빼서 반환한다`() {
        val numberOfPeople = 5
        val actual = CountNumberOfPeople().minus(numberOfPeople)

        assertEquals(actual, 4)
    }

    @Test
    fun `사람 수가 1명인 경우에 뺀다면 계속 1이다`() {
        val numberOfPeople = 1
        val actual = CountNumberOfPeople().minus(numberOfPeople)

        assertEquals(actual, 1)
    }

    @Test
    fun `사람 수를 한 명 더해서 반환한다`() {
        val numberOfPeople = 5
        val actual = CountNumberOfPeople().plus(numberOfPeople)

        assertEquals(actual, 6)
    }

    @Test
    fun `사람 수가 10명인 경우에 더한다면 계속 10이다`() {
        val numberOfPeople = 10
        val actual = CountNumberOfPeople().plus(numberOfPeople)

        assertEquals(actual, 10)
    }
}
