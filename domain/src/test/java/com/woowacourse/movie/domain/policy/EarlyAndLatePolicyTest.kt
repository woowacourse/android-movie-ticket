package com.woowacourse.movie.domain.policy

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalTime

class EarlyAndLatePolicyTest {
    @Test
    fun `조조 영화이고, 영화 가격이 13_000원일 때, 영화 가격은 2_000원 할인 된 11_000원이다`() {
        val movieTime = LocalTime.of(9, 0)
        val earlyAndLatePolicy = EarlyAndLatePolicy(movieTime)
        val ticketPrice = 13_000
        val actual = earlyAndLatePolicy.calculatePrice(ticketPrice)
        val expected = 11_000

        assertThat(actual).isEqualTo(expected)
    }
    @Test
    fun `심야 영화이고, 영화 가격이 13_000원일 때, 영화 가격은 2_000원 할인 된 11_000원이다`() {
        val movieTime = LocalTime.of(20, 0)
        val earlyAndLatePolicy = EarlyAndLatePolicy(movieTime)
        val ticketPrice = 13_000
        val actual = earlyAndLatePolicy.calculatePrice(ticketPrice)
        val expected = 11_000

        assertThat(actual).isEqualTo(expected)
    }
}
