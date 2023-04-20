package com.woowacourse.movie.domain

import com.woowacourse.movie.domain.seat.Rank
import com.woowacourse.movie.domain.seat.SeatPosition

@JvmInline
value class Ticket(val seatPosition: SeatPosition) {
    val rank: Rank
        get() = Rank.valueOf(seatPosition)
}
