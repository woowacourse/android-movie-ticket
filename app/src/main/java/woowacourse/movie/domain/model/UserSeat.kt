package woowacourse.movie.domain.model

import java.io.Serializable

data class UserSeat(val seats: List<Seat>) : Serializable {
    fun removeAt(other: Seat): UserSeat {
        val newSeats = seats.toMutableList()
        newSeats.remove(other)
        return UserSeat(newSeats.toList())
    }

    operator fun plus(other: Seat): UserSeat {
        val newSeats = seats.toMutableList()
        newSeats.add(other)
        return UserSeat(newSeats.toList())
    }
}
