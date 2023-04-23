package com.example.domain.domain

import com.example.domain.model.Money
import com.example.domain.model.SeatRank
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DiscountCalculatorTest {
    @Test
    fun `B등급 좌석 예매 시 무비데이면 10% 할인을 받는다`() {
        val money = Money(SeatRank.B.money)
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(12, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(money, dateTime)

        val expected = Money(9000)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `B등급 좌석 예매 시 영화 시간대가 조조면 2000원 할인을 받는다`() {
        val money = Money(SeatRank.B.money)
        val date = LocalDate.of(2023, 4, 12)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(money, dateTime)

        val expected = Money(8000)

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `B등급 좌석 예매 시 영화 시간대가 야간이면 2000원 할인을 받는다`() {
        val money = Money(SeatRank.B.money)
        val date = LocalDate.of(2023, 4, 12)
        val time = LocalTime.of(22, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(money, dateTime)

        val expected = Money(8000)
        Assert.assertEquals(actual, expected)
    }

    @Test
    fun `B등급 좌석 예매 시 무비데이이고 조조면 10%를 할인 받고 추가로 2000원 할인을 더 받는다`() {
        val money = Money(SeatRank.B.money)
        val date = LocalDate.of(2023, 4, 10)
        val time = LocalTime.of(10, 0)
        val dateTime = LocalDateTime.of(date, time)
        val discountCalculator = DiscountCalculator()
        val actual = discountCalculator.discount(money, dateTime)

        val expected = Money(7000)

        Assert.assertEquals(actual, expected)
    }
}
