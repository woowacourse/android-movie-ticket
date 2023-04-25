package com.example.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SeatTest {
    @ParameterizedTest
    @CsvSource(
        "0, A1",
        "5, B2",
        "10, C3",
        "12, D1",
        "17, E2"
    )
    fun `좌석 인덱스 번호가 주어졌을 때, 열의 문자와 행의 숫자를 이름으로 반환한다`(index: Int, seatName: String) {
        val actual = Seat(index).getSeatName()
        assertEquals(actual, seatName)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 C열이면 S등급을 반환한다`() {
        val actual = Seat(10).getSeatGrade()
        assertEquals(actual, SeatGrade.S_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 D열이면 S등급을 반환한다`() {
        val actual = Seat(12).getSeatGrade()
        assertEquals(actual, SeatGrade.S_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 A열이면 B등급을 반환한다`() {
        val actual = Seat(0).getSeatGrade()
        assertEquals(actual, SeatGrade.B_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 B열이면 B등급을 반환한다`() {
        val actual = Seat(5).getSeatGrade()
        assertEquals(actual, SeatGrade.B_CLASS)
    }

    @Test
    fun `좌석 번호가 주어졌을 떄 E열이면 A등급을 반환한다`() {
        val actual = Seat(17).getSeatGrade()
        assertEquals(actual, SeatGrade.A_CLASS)
    }
}
