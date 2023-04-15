package com.example.domain.discount

import com.example.domain.model.Count
import com.example.domain.model.Money
import com.example.domain.model.Movie
import java.time.LocalDateTime

interface DiscountPolicy {
    fun discount(originMoney: Money, count: Count, movie: Movie, dateTime: LocalDateTime): Money
}
