package com.example.domain.model

import com.example.domain.model.policy.MorningPolicy
import com.example.domain.model.policy.MovieDayPolicy
import com.example.domain.model.policy.NightPolicy
import java.time.LocalDate
import java.time.LocalTime

object PriceCalculator {
    private val policies = listOf(MovieDayPolicy(), MorningPolicy(), NightPolicy())
    fun calculate(
        price: Price,
        playingDate: LocalDate,
        playingTime: LocalTime
    ): Price {
        val calculatePrice = policies.fold(price) { calculatePrice, policy ->
            policy.calculate(playingDate, playingTime, calculatePrice)
        }
        return calculatePrice
    }
}
