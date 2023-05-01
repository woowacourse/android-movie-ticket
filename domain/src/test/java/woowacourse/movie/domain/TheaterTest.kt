package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TheaterTest {

    @Test
    fun `극장의 아이디가 설정된 상태에서 아이디를 다시 변경하려하면 변경되지 않는다`() {
        val theater = Theater(4, 5)
        theater.id = 1L

        theater.id = 2L

        assertThat(theater.id).isEqualTo(1L)
    }
}
