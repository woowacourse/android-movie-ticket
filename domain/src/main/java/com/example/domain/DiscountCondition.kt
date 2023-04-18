package com.example.domain

import java.time.LocalDateTime

interface DiscountCondition {
    fun isSatisfiedBy(dateTime: LocalDateTime): Boolean
}
