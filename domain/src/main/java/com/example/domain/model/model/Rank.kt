package com.example.domain.model.model

import com.example.domain.model.price.Price

enum class Rank(val price: Price) {
    A(Price(12000)), S(Price(15000)), B(Price(10000));

    companion object {
        fun map(row: Int): Rank {
            return when (row) {
                0, 1 -> B
                2, 3 -> S
                4 -> A
                else -> throw IllegalArgumentException(CANT_MATCHING_RANK_ERROR)
            }
        }

        const val CANT_MATCHING_RANK_ERROR = "해당 열에 해당하는 등급이 없습니다."
    }
}
