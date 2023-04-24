package com.example.domain.seat

import com.example.domain.DiscountPrice
import java.time.LocalDateTime

class SeatInfo(
    date: LocalDateTime,
    private var _priceNum: Int = 0,
    private var _countPeople: Int = 0
) {
    val priceNum: Int
        get() = _priceNum
    val countPeople: Int
        get() = _countPeople
    private val discountPrice = DiscountPrice(date)
    private val seats = Seats.makeSeats()

    fun setSeatState(index: Int): Boolean {
        val seat = seats[indexToPosition(index)] ?: throw IllegalArgumentException()
        return if (seat.isSelected) {
            seat.isSelected = false
            _priceNum -= discountPrice.calculateTotalPrice(seat.rank.price)
            _countPeople -= 1
            false
        } else {
            seat.isSelected = true
            _priceNum += discountPrice.calculateTotalPrice(seat.rank.price)
            _countPeople += 1
            true
        }
    }

    fun getSelectedSeats(): List<SeatPosition> {
        return seats.filter {
            it.value.isSelected
        }.map { it.key }
    }
}
