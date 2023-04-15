package com.example.domain.discount

import com.example.domain.model.Count
import com.example.domain.model.Money
import com.example.domain.model.Movie
import java.time.LocalDateTime

class NightDiscountPolicy : DiscountPolicy {
    override fun discount(
        originMoney: Money,
        count: Count,
        movie: Movie,
        dateTime: LocalDateTime
    ): Money {
        return when (dateTime.hour) {
            in TIME_NIGHT -> (originMoney - NIGHT_DISCOUNT * count)
            else -> originMoney
        }
    }

    companion object {
        private val TIME_NIGHT = listOf(0, 20, 21, 22, 23)
        private val NIGHT_DISCOUNT = Money(2000)
    }
}
