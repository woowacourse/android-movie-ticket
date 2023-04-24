package com.example.domain.model.price

import com.example.domain.model.policy.MorningPolicy
import com.example.domain.model.policy.MovieDayPolicy
import com.example.domain.model.policy.NightPolicy
import java.time.LocalDate
import java.time.LocalTime

data class MoviePrice(val price: Int = DEFAULT) {
    private val policies = listOf(MovieDayPolicy(), MorningPolicy(), NightPolicy())

    init {
        require(price >= 0) { throw IllegalArgumentException(MINUS_ERROR) }
    }

    fun calculate(
        playingDate: LocalDate,
        playingTime: LocalTime
    ): MoviePrice {
        val calculatePrice = policies.fold(MoviePrice(price)) { calculatePrice, policy ->
            policy.calculate(playingDate, playingTime, calculatePrice)
        }
        return calculatePrice
    }

    companion object {
        private const val DEFAULT = 13000
        private const val MINUS_ERROR = "가격은 음수가 될 수 없습니다."
    }
}
