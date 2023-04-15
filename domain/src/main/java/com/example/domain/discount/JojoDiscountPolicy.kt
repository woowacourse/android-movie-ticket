package com.example.domain.discount

import com.example.domain.model.Count
import com.example.domain.model.Money
import com.example.domain.model.Movie
import java.time.LocalDateTime

class JojoDiscountPolicy : DiscountPolicy {
    override fun discount(
        originMoney: Money,
        count: Count,
        movie: Movie,
        dateTime: LocalDateTime
    ): Money {
        return when (dateTime.hour) {
            in TIME_MORNING -> (originMoney - JOJO_DISCOUNT * count)
            else -> originMoney
        }
    }

    companion object {
        private val TIME_MORNING = listOf(9, 10, 11)
        private val JOJO_DISCOUNT = Money(2000)
    }
}
