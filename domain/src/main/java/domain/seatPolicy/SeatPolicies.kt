package domain.seatPolicy

import domain.Price
import domain.Seat

data class SeatPolicies(private val seatPolicies: List<SeatPolicy> = DEFAULT_SEAT_POLICIES) {
    fun getSeatRank(seat: Seat): SeatRank {
        for (policy in seatPolicies) if (policy.checkCondition(seat)) return policy.seatRank
        return DEFAULT_RANK
    }

    companion object {
        private val DEFAULT_RANK = SeatRank.B
        private val DEFAULT_SEAT_POLICIES = listOf(RankAPolicy(), RankBPolicy(), RankSPolicy())
    }
}
