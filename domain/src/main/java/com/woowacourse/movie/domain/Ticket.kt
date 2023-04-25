package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.policy.DiscountDecorator
import com.woowacourse.movie.domain.seat.Rank
import com.woowacourse.movie.domain.seat.SeatPosition
import java.time.LocalDateTime

@JvmInline
value class Ticket(val seatPosition: SeatPosition) : Comparable<Ticket> {
    val rank: Rank
        get() = Rank.valueOf(seatPosition)

    fun calculatePrice(rank: Rank, currentDateTime: LocalDateTime): Int =
        DiscountDecorator(currentDateTime).calculatePrice(rank)

    override fun compareTo(other: Ticket): Int =
        compareValuesBy(
            this,
            other,
            {
                it.seatPosition.row.x
            },
            {
                it.seatPosition.col.y
            }
        )
}
