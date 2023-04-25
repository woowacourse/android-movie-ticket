package com.woowacourse.movie.domain.seat

interface RankRule {
    fun getSeatRank(seatPosition: SeatPosition): Rank
}
