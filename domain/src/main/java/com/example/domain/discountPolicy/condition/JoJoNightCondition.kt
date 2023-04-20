package com.example.domain.discountPolicy.condition

import com.example.domain.model.Ticket

class JoJoNightCondition : DiscountCondition {
    override fun isDiscountable(ticket: Ticket): Boolean {
        return ticket.dateTime.hour in TIME_CONDITION
    }

    companion object {
        private val TIME_CONDITION = (0..10) + (20..23)
    }
}
