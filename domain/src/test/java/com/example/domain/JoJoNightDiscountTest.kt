package com.example.domain

import com.example.domain.discountPolicy.apply.JoJoNightDiscount
import com.example.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JoJoNightDiscountTest {
    @Test
    fun `조조 야간 할인은 2000원 할인이다`() {
        val joJoNightDiscount = JoJoNightDiscount()
        val money = Money(13000)

        val actual = joJoNightDiscount.discount(money)
        val expected = Money(11000)
        assertThat(actual).isEqualTo(expected)
    }
}
