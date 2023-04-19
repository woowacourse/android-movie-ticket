package movie

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class NameTest {

    @Test
    fun `영화_이름의_길이는_1_이상이다`() {
        assertDoesNotThrow { Name("l") }
    }

    @Test
    fun `영화_이름의_길이가_0_이하라면_에러를_발생시킨다`() {
        assertThrows<IllegalArgumentException> { Name("") }
    }
}
