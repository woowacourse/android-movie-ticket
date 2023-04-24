package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.seat.Rank
import com.woowacourse.movie.domain.seat.SeatPosition

@JvmInline
value class Ticket(val seatPosition: SeatPosition) : Comparable<Ticket> {
    val rank: Rank
        get() = Rank.valueOf(seatPosition)

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
