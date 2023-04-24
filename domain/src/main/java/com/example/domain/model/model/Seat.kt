package com.example.domain.model.model

import com.example.domain.model.price.MoviePrice
import java.time.LocalDate
import java.time.LocalTime

data class Seat(val row: Int, val column: Int) {

    private val rank = calculateRank()
    private fun calculateRank(): Rank {
        return when (row) {
            0, 1 -> Rank.B
            2, 3 -> Rank.S
            4 -> Rank.A
            else -> throw IllegalArgumentException(CANT_MATCHING_RANK_ERROR)
        }
    }

    fun calculatePrice(playingDate: LocalDate, playingTime: LocalTime): MoviePrice {
        return rank.price.calculate(playingDate, playingTime)
    }

    companion object {
        private const val CANT_MATCHING_RANK_ERROR = "해당 열에 해당하는 등급이 없습니다."
    }
}
