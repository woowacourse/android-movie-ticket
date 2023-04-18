package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.LocalDateTime

class MovieTest {
    @Test
    fun `영화 예약 정보를 반환한다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )
        val reserveDateTime = LocalDateTime.of(2023, 4, 22, 11, 0)
        val ticket = Ticket(1)

        val actual = movie.reserveMovie(reserveDateTime, ticket)!!

        assertAll(
            { assertThat(actual.movie).isEqualTo(movie) },
            { assertThat(actual.dateTime.compareTo(reserveDateTime)).isEqualTo(0) },
            { assertThat(actual.ticket.count).isEqualTo(1) },
        )
    }

    @Test
    fun `존재 하지 않는 상영 일자로 예약하면 null을 반환한다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )
        val reserveDateTime = LocalDateTime.of(2023, 3, 22, 11, 0)
        val ticket = Ticket(1)

        assertThat(movie.reserveMovie(reserveDateTime, ticket)).isNull()
    }
}
