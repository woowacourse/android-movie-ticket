package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class MovieDateTest {
    @Test
    fun `상영일 범위 내에서 현재 날짜부터 마지막 상영일까지 목록을 반환한다`() {
        val movieDate: List<MovieDate> = MovieDate.releaseDates(
            today = LocalDate.of(2023, 4, 10),
            from = LocalDate.of(2023, 4, 1),
            to = LocalDate.of(2023, 4, 28),
        )

        val actual = movieDate.map { it.day }
        val expected = (10..28).toList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 상영일 이전이라면 모든 상영일 목록을 반환한다`() {
        val movieDate: List<MovieDate> = MovieDate.releaseDates(
            today = LocalDate.of(2023, 3, 25),
            from = LocalDate.of(2023, 4, 1),
            to = LocalDate.of(2023, 4, 28),
        )

        val actual = movieDate.map { it.day }
        val expected = (1..28).toList()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 상영일 이후라면 빈 목록을 반환한다`() {
        val movieDate: List<MovieDate> = MovieDate.releaseDates(
            today = LocalDate.of(2023, 4, 29),
            from = LocalDate.of(2023, 4, 1),
            to = LocalDate.of(2023, 4, 28),
        )

        val actual = movieDate.map { it.day }
        val expected = emptyList<MovieDate>()
        assertThat(actual).isEqualTo(expected)
    }
}
