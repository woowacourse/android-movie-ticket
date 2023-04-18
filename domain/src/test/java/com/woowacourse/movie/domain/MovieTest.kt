package com.woowacourse.movie.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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

    @Test
    fun `상영일 범위 내에서 현재 날짜부터 마지막 상영일까지 목록을 반환한다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )

        val runningDate = movie.getRunningDates(
            today = LocalDate.of(2023, 4, 10)
        )

        val actual = runningDate.map { it.dayOfMonth }
        val expected = (10..30).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 상영일 이전이라면 모든 상영일 목록을 반환한다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )

        val runningDate = movie.getRunningDates(
            today = LocalDate.of(2023, 3, 25)
        )

        val actual = runningDate.map { it.dayOfMonth }
        val expected = (1..30).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `오늘이 상영일 이후라면 빈 목록을 반환한다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )

        val runningDate = movie.getRunningDates(
            today = LocalDate.of(2023, 5, 1)
        )

        val actual = runningDate.map { it.dayOfMonth }
        val expected = emptyList<LocalDate>()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말 영화 상영시간은 오전 9시부터 자정까지 두 시간 간격이다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )

        val date = LocalDate.of(2023, 4, 16)
        val currentTime = LocalTime.of(0, 0)
        val runningTime = movie.getRunningTimes(
            date,
            currentTime
        )

        val actual = runningTime.map { it.hour }
        val expected = (9 until 24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `평일 영화 상영시간은 오전 10시부터 자정까지 두 시간 간격이다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )

        val date = LocalDate.of(2023, 4, 17)
        val currentTime = LocalTime.of(0, 0)
        val runningTime = movie.getRunningTimes(
            date,
            currentTime
        )

        val actual = runningTime.map { it.hour }
        val expected = (10 until 24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `주말 현재 시간이 10시면 영화 상영시간은 오전 11시부터 자정까지 두 시간 간격이다`() {
        val movie = Movie(
            1,
            "더 퍼스트 슬램덩크",
            LocalDate.of(2023, 4, 1),
            LocalDate.of(2023, 4, 30),
            124,
            "전국 제패를 꿈꾸는 북산고 농구부 5인방의 꿈과 열정, 멈추지 않는 도전을 그린 만화"
        )

        val date = LocalDate.of(2023, 4, 16)
        val currentTime = LocalTime.of(10, 0)
        val runningTime = movie.getRunningTimes(
            date,
            currentTime
        )

        val actual = runningTime.map { it.hour }
        val expected = (9 until 24 step 2).toList()

        assertThat(actual).isEqualTo(expected)
    }
}
