package woowacourse.movie.model

import java.io.Serializable

class Seats : Serializable {
    var seats = mutableListOf<Seat>()
        private set

    fun totalPrice() = seats.sumOf { it.price(it.grade()) }

    operator fun contains(seat: Seat): Boolean = seat in seats

    fun addSeat(seat: Seat) {
        require(seat !in seats) { "이미 선택된 자리 입니다" }
        seats.add(seat)
    }

    fun removeSeat(seat: Seat) {
        seats.remove(seat)
    }
}
