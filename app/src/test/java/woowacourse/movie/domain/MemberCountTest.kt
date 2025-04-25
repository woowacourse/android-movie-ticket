package woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import kotlin.test.Test

class MemberCountTest {
    @Test
    fun `1명 미만의 인원을 가질 수 없다`() {
        assertThatThrownBy {
            MemberCount(0)
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("영화 예매 수는 1명이상이어야합니다.")
    }

    @Test
    fun `인원을 늘리면 1명이 늘어난다`() {
        val result = MemberCount(1).increase()
        assertThat(result.getOrNull()).isEqualTo(MemberCount(2))
    }

    @Test
    fun `인원을 줄이면 1명이 줄어든다`() {
        val result = MemberCount(2).decrease()
        assertThat(result.getOrNull()).isEqualTo(MemberCount(1))
    }

    @Test
    fun `1명일 때 인원을 줄일 수 없다`() {
        val result = MemberCount(1).decrease()
        assertThat(result.getOrNull()).isNull()
    }
}
