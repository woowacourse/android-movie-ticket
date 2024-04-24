package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.Seat
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
