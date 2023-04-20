package com.example.domain.discountPolicy.condition

import com.example.domain.model.Ticket

interface DiscountCondition {
    fun isDiscountable(ticket: Ticket): Boolean
}
