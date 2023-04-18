package domain.movie

import org.junit.Test
import woowacourse.movie.domain.movie.Name

internal class NameTest {

    @Test
    fun `영화_이름의_길이는_1_이상이다`() {
        Name("l")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `영화_이름의_길이가_0_이하라면_에러를_발생시킨다`() {
        Name("")
    }
}
