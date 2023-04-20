package domain

import domain.seatPolicy.SeatPolicies

data class Seat(val row: Int, val col: Int) {
    fun getPrice(seatPolicies: SeatPolicies): Price {
        return seatPolicies.getPrice(this)
    }
}