package woowacourse.movie.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {

    @Test
    fun `선택된 좌석의 총합을 구한다`() {
        val ranks = listOf(Rank.A, Rank.B, Rank.S)
        val expected = 37_000

        val actual = Rank.calculateTotalPrice(ranks)

        assertThat(actual).isEqualTo(expected)
    }
}