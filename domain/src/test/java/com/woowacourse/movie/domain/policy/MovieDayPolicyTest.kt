package com.woowacourse.movie.domain.policy

import com.woowacourse.movie.domain.MovieDate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieDayPolicyTest {
    @Test
    fun `무비 데이고, 영화 가격이 13_000원일 때, 영화 가격은 10% 할인 된 11_700원이다`() {
        val movieDate = MovieDate(2023, 4, 20)
        val movieDay = MovieDayPolicy(movieDate)
        val ticketPrice = 13_000
        val actual = movieDay.calculatePrice(ticketPrice)
        val expected = 11_700

        assertThat(actual).isEqualTo(expected)
    }
}
