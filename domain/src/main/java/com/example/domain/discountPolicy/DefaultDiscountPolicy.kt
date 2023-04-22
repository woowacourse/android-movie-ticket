package com.example.domain.discountPolicy

import com.example.domain.discountPolicy.policy.Policy
import com.example.domain.model.Money
import com.example.domain.model.Ticket

class DefaultDiscountPolicy(
    private val policies: List<Policy> = listOf(
        Policy.MovieDayPolicy(),
        Policy.JoJoNightPolicy()
    )
) : DiscountPolicy {

    override fun discount(ticket: Ticket): Money {
        return policies.fold(ticket.originMoney) { money, policy ->
            if (policy.discountCondition.isDiscountable(ticket)) {
                return@fold policy.discount.discount(money)
            }
            money
        }
    }
}
