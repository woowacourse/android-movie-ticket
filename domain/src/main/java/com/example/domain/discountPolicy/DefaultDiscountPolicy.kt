package com.example.domain.discountPolicy

import com.example.domain.discountPolicy.policy.Policy
import com.example.domain.model.Money
import com.example.domain.model.Movie
import com.example.domain.model.seat.SeatPosition
import com.example.domain.model.seat.SeatRank
import java.time.LocalDateTime

class DefaultDiscountPolicy(
    private val policies: List<Policy> = listOf(
        Policy.MovieDayPolicy(),
        Policy.JoJoNightPolicy()
    )
) : DiscountPolicy {

    override fun discount(
        movie: Movie,
        dateTime: LocalDateTime,
        seatPosition: SeatPosition
    ): Money {
        val originMoney = SeatRank.from(seatPosition).money
        return policies.fold(originMoney) { money, policy ->
            if (policy.discountCondition.isDiscountable(movie, dateTime, seatPosition)) {
                return@fold policy.discount.discount(money)
            }
            money
        }
    }
}
