package com.example.domain.model

import com.example.domain.discountPolicy.DiscountPolicy

class Tickets(tickets: List<Ticket>) {
    private val _tickets: List<Ticket> = tickets.toList()
    val tickets: List<Ticket>
        get() = _tickets.toList()

    fun getTotalOriginMoney(): Money = Money(tickets.sumOf { it.originMoney.value })

    fun getTotalDiscountApplyMoney(discountPolicy: DiscountPolicy): Money =
        Money(tickets.sumOf { it.getDiscountMoney(discountPolicy).value })
}
