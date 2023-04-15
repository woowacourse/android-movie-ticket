package com.example.domain.discountPolicy

import com.example.domain.discountPolicy.apply.Discount
import com.example.domain.discountPolicy.apply.JoJoNightDiscount
import com.example.domain.discountPolicy.apply.MovieDayDiscount
import com.example.domain.discountPolicy.condition.DiscountCondition
import com.example.domain.discountPolicy.condition.JoJoNightCondition
import com.example.domain.discountPolicy.condition.MovieDayCondition
import com.example.domain.model.Money
import com.example.domain.model.Reservation

class DiscountPolicy {
    private val policy: Map<DiscountCondition, Discount> = mapOf(
        MovieDayCondition() to MovieDayDiscount(),
        JoJoNightCondition() to JoJoNightDiscount()
    )

    fun discount(reservation: Reservation): Money {
        val discountApplyMoney = policy.keys.fold(TICKET_MONEY) { money, condition ->
            if (condition.isDiscountable(reservation)) {
                return@fold policy[condition]!!.discount(money)
            }
            money
        }
        return discountApplyMoney * reservation.count
    }

    companion object {
        private val TICKET_MONEY = Money(13000)
    }
}
