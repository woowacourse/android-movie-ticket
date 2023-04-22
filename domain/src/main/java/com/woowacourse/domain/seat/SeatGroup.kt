package com.woowacourse.domain.seat

class SeatGroup(val seats: List<Seat> = emptyList()) {

    fun sorted(): SeatGroup = SeatGroup(seats.sorted())

    fun canAdd(count: Int): Boolean = seats.size < count

    fun add(seat: Seat): SeatGroup = SeatGroup(seats + seat)

    fun remove(seat: Seat): SeatGroup = SeatGroup(seats - seat)
}
