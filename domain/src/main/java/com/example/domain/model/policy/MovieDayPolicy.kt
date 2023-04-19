package com.example.domain.model.policy

import java.time.LocalDate
import java.time.LocalTime

class MovieDayPolicy : DiscountPolicy() {
    override fun getDiscountPrice(price: com.example.domain.model.Price): com.example.domain.model.Price {
        return com.example.domain.model.Price((price.price * 0.9).toInt())
    }

    override fun isAvailable(date: LocalDate, time: LocalTime): Boolean {
        return date.dayOfMonth == 10 || date.dayOfMonth == 20 || date.dayOfMonth == 30
    }
}
