package com.example.domain.model.policy

import com.example.domain.model.price.MoviePrice
import java.time.LocalDate
import java.time.LocalTime

abstract class DiscountPolicy {
    abstract fun getDiscountPrice(price: MoviePrice): MoviePrice
    abstract fun isAvailable(date: LocalDate, time: LocalTime): Boolean
    fun calculate(date: LocalDate, time: LocalTime, price: MoviePrice): MoviePrice {
        if (isAvailable(date, time)) return getDiscountPrice(price)
        return price
    }
}
