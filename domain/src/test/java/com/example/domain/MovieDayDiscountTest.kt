package com.example.domain

import com.example.domain.discountPolicy.apply.MovieDayDiscount
import com.example.domain.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieDayDiscountTest {
    @Test
    fun `무비 데이에는 10% 할인 받는다`() {
        val movieDayDiscount = MovieDayDiscount()
        val money = Money(13000)

        val actual = movieDayDiscount.discount(money)
        val expected = Money(11700)

        assertThat(actual).isEqualTo(expected)
    }
}
