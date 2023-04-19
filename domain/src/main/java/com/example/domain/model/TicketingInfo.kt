package com.example.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class TicketingInfo private constructor(
    val title: String,
    val playingDate: LocalDate,
    val playingTime: LocalTime,
    val count: Int,
    val price: Price,
    val payment: Payment
) : java.io.Serializable {
    companion object {
        fun of(
            title: String,
            playingDate: LocalDate,
            playingTime: LocalTime,
            count: Int,
            price: Price,
            payment: Payment
        ): TicketingInfo {
            val calculatePrice = PriceCalculator.calculate(price, playingDate, playingTime)
            return TicketingInfo(title, playingDate, playingTime, count, calculatePrice, payment)
        }
    }
}
