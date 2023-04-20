package movie

import data.PricePolicyInfo
import data.Seat
import data.SeatPosition
import movie.pricePolicy.NormalPricePolicy
import movie.pricePolicy.PricePolicy
import java.time.LocalDateTime

class SeatSelection(
    private val cinemaConfigure: CinemaConfigure = CinemaConfigure,
    private val pricePolicy: PricePolicy = NormalPricePolicy(),
) {
    private val seats = mutableMapOf<Seat, Boolean>().apply {
        cinemaConfigure.seats.forEach {
            put(Seat(it.key, it.value, false), false)
        }
    }

    val sizeOfSelection: Int
        get() = seats.filter { it.value }.count()

    val selection: List<Seat>
        get() = seats.filter { it.value }.map { it.key }

    operator fun get(seatPosition: SeatPosition): Boolean {
        return seats[findByPosition(seatPosition)] ?: throw IllegalArgumentException()
    }

    fun selectSeat(position: SeatPosition) {
        val seat = findByPosition(position)
        seats[seat]?.let {
            seats[seat] = it.not()
        }
    }

    fun getTotalPrice(localDateTime: LocalDateTime): Int = seats
        .filter { it.value }
        .map { PricePolicyInfo(cinemaConfigure.getPriceOf(it.key.seatClass), localDateTime) }
        .sumOf { pricePolicy.calculatePrice(it).price }

    private fun findByPosition(seatPosition: SeatPosition): Seat {
        return seats.keys.find { it.position == seatPosition } ?: throw IllegalArgumentException()
    }
}
