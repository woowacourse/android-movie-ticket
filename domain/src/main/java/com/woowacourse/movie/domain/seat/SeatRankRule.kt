package com.woowacourse.movie.domain.seat

object SeatRankRule : RankRule {
    private val bRankRange = (0..1)
    private val aRankRange = (2..3)
    override fun getSeatRank(seatPosition: SeatPosition): Rank =
        when (seatPosition.row.x) {
            in bRankRange -> Rank.B
            in aRankRange -> Rank.A
            else -> Rank.S
        }
}
