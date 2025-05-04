package woowacourse.movie.domain.seat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingSeats(
    val value: Int,
    private val _seats: MutableList<Seat> = mutableListOf(),
) : Parcelable {
    val seats: List<Seat>
        get() = _seats

    init {
        require(value >= MINIMUM_NUMBER_OF_PEOPLE) { ERROR_PEOPLE_OVER_ONE }
    }

    fun add(seat: Seat) {
        _seats.add(seat)
    }

    fun remove(seat: Seat) {
        _seats.remove(seat)
    }

    fun calculateTicketPrices(): Int {
        return _seats.sumOf { seat -> seat.price() }
    }

    fun isSelectedAll(): Boolean = this.value == this._seats.size

    companion object {
        private const val MINIMUM_NUMBER_OF_PEOPLE = 1
        private const val ERROR_PEOPLE_OVER_ONE = "영화 예매 수는 1명이상이어야합니다."
    }
}
