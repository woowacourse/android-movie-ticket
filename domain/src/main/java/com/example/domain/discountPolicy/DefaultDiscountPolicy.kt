package com.example.domain.discountPolicy

import com.example.domain.discountPolicy.apply.Discount
import com.example.domain.discountPolicy.apply.JoJoNightDiscount
import com.example.domain.discountPolicy.apply.MovieDayDiscount
import com.example.domain.discountPolicy.condition.DiscountCondition
import com.example.domain.discountPolicy.condition.JoJoNightCondition
import com.example.domain.discountPolicy.condition.MovieDayCondition
import com.example.domain.model.Money
import com.example.domain.model.Ticket

class DefaultDiscountPolicy : DiscountPolicy {
    private val policy: Map<DiscountCondition, Discount> = mapOf(
        MovieDayCondition() to MovieDayDiscount(),
        JoJoNightCondition() to JoJoNightDiscount()
    )

    override fun discount(ticket: Ticket): Money {
        return policy.keys.fold(ticket.originMoney) { money, condition ->
            if (condition.isDiscountable(ticket)) {
                return@fold policy[condition]?.discount(money) ?: money
            }
            money
        }
    }
}
