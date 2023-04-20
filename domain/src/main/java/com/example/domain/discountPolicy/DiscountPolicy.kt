package com.example.domain.discountPolicy

import com.example.domain.model.Money
import com.example.domain.model.Ticket

interface DiscountPolicy {
    fun discount(ticket: Ticket): Money
}
