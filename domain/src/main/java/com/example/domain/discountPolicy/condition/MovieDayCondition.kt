package com.example.domain.discountPolicy.condition

import com.example.domain.model.Ticket

class MovieDayCondition : DiscountCondition {
    override fun isDiscountable(ticket: Ticket): Boolean {
        return ticket.dateTime.dayOfMonth in MOVIE_DAYS
    }

    companion object {
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}
