package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RowTest {
    @Test
    fun `존재하지 않는 열을 입력하면 예외를 던진다`() {
        assertThrows<IllegalArgumentException> { Row(5) }
    }

    @Test
    fun `존재하는 열을 입력하면 값을 가진다`() {
        //given
        val expected = 2

        //when
        val actual = Row(2).value

        //then
        assertThat(actual).isEqualTo(expected)
    }
}